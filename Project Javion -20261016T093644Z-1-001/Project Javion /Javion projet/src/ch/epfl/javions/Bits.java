package ch.epfl.javions;

import java.util.Objects;

/**
 * Classe qui fait des opérations sur les bits de valeur de type long
 */
public class Bits {

    /**
     * Constructeur privé
     */
    private Bits(){}

    /**
     *  Extrait du vecteur de 64 bits value la plage de size bits commençant au bit d'index start
     *
     * @param value valeur dont on veut extraire un chaine de bits
     * @param start index à partir du quelle on extrait la chaîne de bits (inclus)
     * @param size taille de la chaîne de bit à extraire
     * @return chaîne de bits
     */
    public static int extractUInt(long value, int start, int size){
        Preconditions.checkArgument(size > 0 && size < Integer.SIZE );
        Objects.checkFromIndexSize(start, size, Long.SIZE);

        value <<= Long.SIZE - (size + start);
        value >>>= Long.SIZE - size ;
        return (int) value;
    }

    /**
     * Retourne vrai si le bit de value d'index donné vaut 1
     *
     * @param value valeur du
     * @param index valeur dont on veut tester un de ses bits
     * @return boolean True/False
     */

    public static boolean testBit(long value, int index){
        Objects.checkIndex( index, Long.SIZE);

        value >>>= index;

        return (value  & 1) == 1;
    }
}
