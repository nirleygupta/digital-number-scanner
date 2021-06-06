package com.nirley.digital.number;

import com.nirley.digital.number.chunk.config.ChunkConfig;
import com.nirley.digital.number.load.ConsolePrinter;
import com.nirley.digital.number.load.Printer;
import com.nirley.digital.number.service.DigitalNumberService;

import java.nio.file.Paths;

public class DigitalNumberApplication {

    public static void main(String[] args) throws Exception {
        String filePath = Paths.get("src","main", "resources", "sampleInput.txt")
                .toString();
        Printer consolePrinter = new ConsolePrinter();
        DigitalNumberService digitalNumberService = DigitalNumberService.builder()
                .path(filePath)
                .printer(consolePrinter)
                .chunkConfig(new ChunkConfig(3,3))
                .build();

        digitalNumberService.scanAndPrintDigitalNumber();
    }
}
