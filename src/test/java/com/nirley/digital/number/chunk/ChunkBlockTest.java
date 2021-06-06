package com.nirley.digital.number.chunk;

import com.nirley.digital.number.chunk.config.ChunkConfig;
import com.nirley.digital.number.chunk.exception.ChunkValidationException;
import com.nirley.digital.number.chunk.validation.ChunkValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ChunkBlockTest {

    ChunkBlock classUnderTest;
    List<String> lines;
    ChunkConfig chunkConfig4by4;

    @Before
    public void setup() {
        lines = Arrays.asList(" - ","| |","|_|");
        chunkConfig4by4 = new ChunkConfig(4,4);
    }

    @Test(expected =ChunkValidationException.class)
    public void testWrongConfig() {
        classUnderTest = new ChunkBlock(3, lines);
        classUnderTest.generateChunks(chunkConfig4by4);
    }

    @Test
    public void testSquareChunks() {
        ChunkConfig chunkConfig = new ChunkConfig(3,3);

        classUnderTest = new ChunkBlock(3, lines);
        List<Chunk> chunks = classUnderTest.generateChunks(chunkConfig)
                .collect(Collectors.toList());

        assertEquals(1,chunks.size());
        assertEquals(lines.stream().collect(Collectors.joining()), chunks.get(0).getSource());

    }

    @Test
    public void testRectangleChunks() {
        ChunkConfig chunkConfig = new ChunkConfig(2,3);
        List<String> rectangleLines = Arrays.asList(" - ","| |");
        classUnderTest = new ChunkBlock(2, rectangleLines);
        List<Chunk> chunks = classUnderTest.generateChunks(chunkConfig)
                .collect(Collectors.toList());

        assertEquals(1,chunks.size());
        assertEquals(rectangleLines.stream()
                .collect(Collectors.joining()),
                chunks.get(0).getSource());

    }

}