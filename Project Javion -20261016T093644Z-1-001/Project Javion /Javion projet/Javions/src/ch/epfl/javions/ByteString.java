package ch.epfl.javions;

import java.util.Arrays;
import java.util.HexFormat;
import java.util.Objects;

public final class ByteString {

    private byte[] bytes;

    public ByteString(byte[] bytes){
        this.bytes = bytes.clone();
    }

    public static ByteString ofHexadecimalString(String hexString) {
        HexFormat hf = HexFormat.of().withUpperCase();
        Preconditions.checkArgument( hexString.length() % 2 == 0);
        return new ByteString( hf.parseHex(hexString));
        }

    public int size() {
        return bytes.length;
    }

    public int byteAt(int index){
        Objects.checkIndex(index, size());
        return bytes[index] & 0xff;
    }

    public long bytesInRange(int fromIndex, int toIndex){
        Preconditions.checkArgument(toIndex - fromIndex  < Long.SIZE/8);
        Objects.checkFromToIndex(fromIndex, toIndex-1, size());
        long range = 0;
        for( int i = fromIndex; i < toIndex -1 ; i++){
            range += byteAt(i);
            range <<= 8;
        }
        return range + byteAt(toIndex - 1);
    }

    @Override
    public boolean equals(Object thatO) {
        if (thatO instanceof ByteString that) {
            if (Arrays.equals(bytes, that.bytes)) return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }

    @Override
    public String toString(){
        HexFormat hf = HexFormat.of().withUpperCase();
        return hf.formatHex(bytes);
    }
}


