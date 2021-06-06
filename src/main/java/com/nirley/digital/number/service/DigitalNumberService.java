package com.nirley.digital.number.service;

import com.nirley.digital.number.chunk.ChunkBlock;
import com.nirley.digital.number.chunk.config.ChunkConfig;
import com.nirley.digital.number.chunk.reader.FileChunkReader;
import com.nirley.digital.number.load.Printer;
import com.nirley.digital.number.response.ResponseWrapper;
import com.nirley.digital.number.transform.ChunkTransformer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.nirley.digital.number.constants.Constants.ILLEGAL_IDENTIFIER;

@Builder
@Getter
@AllArgsConstructor
public class DigitalNumberService {
    String path;

    ChunkConfig chunkConfig;

    Printer printer;

    protected List<String> checkErrorAndTranslate(List<ResponseWrapper> translatedList) {

        List<String> digitsList =  translatedList.stream()
                .map(ResponseWrapper::toString)
                .collect(Collectors.toList());

        translatedList.stream()
                .filter(ResponseWrapper::isError)
                .findFirst()
                .ifPresent(response -> digitsList.add(ILLEGAL_IDENTIFIER));

       return digitsList;
    }

    // scanner and printer method
    public void scanAndPrintDigitalNumber() throws IOException {
        try (FileChunkReader bufferedReader = new FileChunkReader(path)) {
            ChunkBlock chunkBlock = null;

            while ((chunkBlock = bufferedReader.readChunkLine(chunkConfig.getBreath())) != null) {
                List<ResponseWrapper> translatedList = transformChunksToDigits(chunkBlock);
                List<String> translatedStrings = checkErrorAndTranslate(translatedList);
                printer.print(translatedStrings.stream());
            }
        }
    }

    public List<String> scanDigitalNumberToList() throws IOException {

        List<String> result=new ArrayList<>();

        try (FileChunkReader bufferedReader = new FileChunkReader(path)) {
            ChunkBlock chunkBlock = null;
            while ((chunkBlock = bufferedReader.readChunkLine(chunkConfig.getBreath())) != null) {
                List<ResponseWrapper> translatedList = transformChunksToDigits(chunkBlock);
                List<String> translatedStrings = checkErrorAndTranslate(translatedList);
                result.add(translatedStrings.stream()
                        .collect(Collectors
                                .joining("")));
            }

        }

        return result;

    }

    protected List<ResponseWrapper> transformChunksToDigits(ChunkBlock chunkBlock) {
        return chunkBlock.generateChunks(getChunkConfig())
                .parallel()
                .map(ChunkTransformer::transform)
                .collect(Collectors.toList());
    }

}
