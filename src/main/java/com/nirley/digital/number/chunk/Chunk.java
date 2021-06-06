package com.nirley.digital.number.chunk;

import com.nirley.digital.number.chunk.config.ChunkConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class Chunk {
    private ChunkConfig chunkConfig;
    private String source;
}
