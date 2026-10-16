package ch.epfl.javions.demodulation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class SamplesDecoder {
    private InputStream stream;
    private int batchSize;

    public SamplesDecoder(InputStream stream, int batchSize){
        Objects.requireNonNull(batchSize);
        if(batchSize < 0) throw new IllegalArgumentException("negative value");

        this.stream = stream;
    }

    private int readBatch(short[] batch) throws IOException {
        byte[] streamByte = new byte[16];
        stream.readNBytes(streamByte, 0, batchSize);
        return 0;
    }
}
