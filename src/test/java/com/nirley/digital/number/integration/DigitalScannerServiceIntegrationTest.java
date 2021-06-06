package com.nirley.digital.number.integration;

import com.nirley.digital.number.chunk.config.ChunkConfig;
import com.nirley.digital.number.load.ConsolePrinter;
import com.nirley.digital.number.load.Printer;
import com.nirley.digital.number.service.DigitalNumberService;

import java.io.IOException;
import java.util.List;

public class DigitalScannerServiceIntegrationTest extends BaseScannerServiceIntegrationTest {

    @Override
    protected List<String> performScanning(String inputFilePath) {
        ChunkConfig chunkConfig = new ChunkConfig(3,3);
        Printer printer= new ConsolePrinter();
        DigitalNumberService digitalNumberScanner = new DigitalNumberService(inputFilePath,
                chunkConfig,
                printer);
        List<String> result=null;
        try {
            result = digitalNumberScanner.scanDigitalNumberToList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }
}
