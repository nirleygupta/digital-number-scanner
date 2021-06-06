package com.nirley.digital.number.chunk.reader;

import com.nirley.digital.number.chunk.ChunkBlock;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Predicate;

// Reader for extracting chunks
public class FileChunkReader implements Closeable {

    private static Predicate<String> defaultDelimitorFun =
            line -> line.trim().isEmpty();

    private BufferedReader reader;

    @Getter
    private Predicate<String> delimitorPredicate;

    public FileChunkReader(String path) throws IOException {
        this(path, defaultDelimitorFun);
    }

    public FileChunkReader(String path, Predicate<String> delimitorPredicate) throws IOException {
        this.reader = Files.newBufferedReader(Paths.get(path));
        this.delimitorPredicate = delimitorPredicate;
    }

    // Read chunkblocks of dynamic size (block size is length of lines)
    public ChunkBlock readChunkLine(int blockSize) throws IOException {
        String line;
        int currentChunkSize = 0;
        ChunkBlock chunkBlock = new ChunkBlock(blockSize);

        while ((line = reader.readLine()) != null && currentChunkSize < blockSize) {
            if (!delimitorPredicate.test(line)) {
                currentChunkSize++;
                chunkBlock.append(line);
            }
        }

        return Optional.of(chunkBlock)
                .filter(cl -> !cl.isEmpty())
                .orElse(null);
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
