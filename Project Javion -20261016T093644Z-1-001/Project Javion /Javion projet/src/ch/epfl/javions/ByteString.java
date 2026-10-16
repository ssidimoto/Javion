package ch.epfl.javions;

import java.util.Arrays;
import java.util.HexFormat;
import java.util.Objects;

/**
 * Représente une chaîne (séquence) d'octets
 *
 * @author Edouard Fousson (ug6Coi2l)
 * @author Anas Sidi Mohamed (Vouboh4v)
 *
 */
public final class ByteString {

    /**
     * Représente une chaîne (séquence) d'octets
     */
    private final byte[] bytes;

    /**
     * Retourne une chaîne d'octets dont le contenu est celui du tableau passé en argument
     *
     * @param bytes tableau d'octets
     */
    public ByteString(byte[] bytes){
        this.bytes = bytes.clone();
    }

    /**
     * Retourne la chaîne d'octets dont la chaîne passée en argument est la représentation hexadécimale
     *
     * @param hexString représentation hexadécimale
     * @return la chaîne d'octets dont la chaîne passée en argument est la représentation hexadécimale
     */
    public static ByteString ofHexadecimalString(String hexString) {
        HexFormat hf = HexFormat.of().withUpperCase();
        Preconditions.checkArgument( hexString.length() % 2 == 0);
        return new ByteString( hf.parseHex(hexString));
        }

    /**
     * Retourne la taille de la chaîne
     *
     * @return la taille de la chaîne
     */
    public int size() {
        return bytes.length;
    }

    /**
     * Retourne l'octet (interprété en non signé) à l'index donné
     *
     * @param index index donné
     * @return l'octet (interprété en non signé)
     */
    public int byteAt(int index){
        Objects.checkIndex(index, size());
        return  Byte.toUnsignedInt(bytes[index]);
    }

    /**
     * Retourne les octets compris entre les index fromIndex (inclus) et toIndex (exclu) sous la forme d'une valeur de type long
     *
     * @param fromIndex index de départ
     * @param toIndex index final
     * @return le long recherché
     */
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

    /**
     * Retourne vrai ssi la valeur qu'on lui passe est aussi une instance de ByteString et que ses octets sont identiques à ceux du récepteur
     *
     * @param thatO instance à tester
     * @return vrai ssi la valeur qu'on lui passe est aussi une instance de ByteString et que ses octets sont identiques à ceux du récepteur
     */
    @Override
    public boolean equals(Object thatO) {
        if (thatO instanceof ByteString that) {
            if (Arrays.equals(bytes, that.bytes)) return true;
        }
        return false;
    }

    /**
     * Retourne la valeur retournée par la méthode hashCode de Arrays
     *
     * @return la valeur retournée par la méthode hashCode de Arrays
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }

    /**
     * Retourne une représentation des octets de la chaîne en hexadécimal
     *
     * @return une représentation des octets de la chaîne en hexadécimal
     */
    @Override
    public String toString(){
        HexFormat hf = HexFormat.of().withUpperCase();
        return hf.formatHex(bytes);
    }
}


