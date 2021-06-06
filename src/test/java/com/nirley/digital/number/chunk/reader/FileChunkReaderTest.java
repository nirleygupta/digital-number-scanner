package com.nirley.digital.number.chunk.reader;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileChunkReaderTest {
    private static final String dummyFilePath = "DUMMY_FILE_PATH";
    private static final String delimitorLine = "  ";
    private static final String wrongDelimitorLine = " _a_ ";
    private FileChunkReader classUnderTest;

    @Test(expected = IOException.class)
    public void testFileNotExists() throws IOException {
        classUnderTest = new FileChunkReader(dummyFilePath);
    }

    @Test
    public void testDefaultDelimitor() throws IOException {
        String sampleFilePath = Paths.get("src","test", "resources","unitTest.")
                .toString();
        classUnderTest = new FileChunkReader(sampleFilePath);
        assertTrue(classUnderTest.getDelimitorPredicate().test(delimitorLine));
        assertFalse(classUnderTest.getDelimitorPredicate().test(wrongDelimitorLine));
    }

}