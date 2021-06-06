package com.nirley.digital.number.chunk.validation;

import com.nirley.digital.number.chunk.ChunkBlock;
import com.nirley.digital.number.chunk.config.ChunkConfig;

import java.util.function.BiPredicate;

public class ChunkValidator {

    private static final BiPredicate<ChunkBlock, ChunkConfig> validateBlockLength =
            (block, config) -> block.getLines()
                    .size() == config.getBreath();

    private static final BiPredicate<ChunkBlock, ChunkConfig> validateBlockLines =
            (block, config) -> !block.getLines()
                    .stream()
                    .filter(line -> line.length() % config.getLength() != 0)
                    .findFirst()
                    .isPresent();

    public static boolean isValid(ChunkBlock chunkBlock, ChunkConfig chunkConfig) {
        return validateBlockLength.and(validateBlockLines)
                .test(chunkBlock, chunkConfig);
    }


}
