package com.nirley.digital.number.chunk;

import com.nirley.digital.number.chunk.config.ChunkConfig;
import com.nirley.digital.number.chunk.exception.ChunkValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.nirley.digital.number.chunk.validation.ChunkValidator.isValid;

@Getter
@ToString
@AllArgsConstructor
public class ChunkBlock {

    int size;
    List<String> lines;

    public ChunkBlock(int size) {
        this(size, new ArrayList(size));
    }

    public ChunkBlock append(String line) {
        getLines().add(line);
        return this;
    }

    public boolean isEmpty() {
        return getLines().isEmpty();
    }

    public Stream<Chunk> generateChunks(ChunkConfig chunkConfig) {
        if (!isValid(this, chunkConfig)) {
            throw new ChunkValidationException(String
                    .format("Invalid Chunk Config %s for block %s", chunkConfig, this));
        }

        int numberOfChunks = getLines()
                .stream()
                .map(String::length)
                .max(Integer::compareTo)
                .orElse(0) / getSize();

        List<StringBuilder> stringBuilderListForChunks = IntStream.range(0, numberOfChunks)
                .mapToObj(count -> new StringBuilder())
                .collect(Collectors.toList());

        getLines()
                .stream()
                .forEach(line -> IntStream.range(0, numberOfChunks)
                        .forEach(index -> stringBuilderListForChunks.get(index)
                                .append(line,
                                        getStartIndex(chunkConfig, index),
                                        getEndIndex(chunkConfig, index))));


        return stringBuilderListForChunks.stream()
                .map(builder -> new Chunk(chunkConfig, builder.toString()));
    }

    private int getEndIndex(ChunkConfig chunkConfig, int index) {
        return getStartIndex(chunkConfig, index) + chunkConfig.getLength();
    }

    private int getStartIndex(ChunkConfig chunkConfig, int index) {
        return index * chunkConfig.getLength();
    }
}
