package com.nirley.digital.number.chunk.validation;

import com.nirley.digital.number.chunk.ChunkBlock;
import com.nirley.digital.number.chunk.config.ChunkConfig;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChunkValidatorTest {
    @Test
    public void validateCorrectBlockandChunk() {
        ChunkBlock block = new ChunkBlock(3, Arrays.asList("aba","bcf","hfg"));
        ChunkConfig chunkConfig = new ChunkConfig(3,3);

        assertTrue(ChunkValidator.isValid(block,chunkConfig));
    }

    @Test
    public void validateRectangleChunk() {
        ChunkBlock block = new ChunkBlock(3, Arrays.asList("abad","bcfd","hfgd"));
        ChunkConfig chunkConfig = new ChunkConfig(3,4);

        assertTrue(ChunkValidator.isValid(block,chunkConfig));
    }

    @Test
    public void validateCorrectBlockandWrongChunk() {
        ChunkBlock block = new ChunkBlock(3, Arrays.asList("abad","bcfd","hfgd"));
        ChunkConfig chunkConfig = new ChunkConfig(3,3);

        assertFalse(ChunkValidator.isValid(block,chunkConfig));

    }

    @Test
    public void validateWrongBlockLines() {
        ChunkBlock block = new ChunkBlock(3, Arrays.asList("abad","bcfd"));
        ChunkConfig chunkConfig = new ChunkConfig(3,4);

        assertFalse(ChunkValidator.isValid(block,chunkConfig));
    }
}