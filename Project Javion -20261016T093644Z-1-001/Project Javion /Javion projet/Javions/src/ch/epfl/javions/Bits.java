package ch.epfl.javions;

import java.util.Objects;

public class Bits {
    private Bits(){}
    public static int extractUInt(long value, int start, int size){

        //utilise checkArguments pour throw des illegalargumentsexceptions, elle sert a ca la classe + faut verifier que start est pas negatif aussi je pense
        //Preconditions.checkArgument(size > 0 && size < Integer.SIZE );
        //Objects.checkFromIndexSize(start, size, Long.SIZE); //nvl méthode recommandée

        if(size <= 0 || size >= Integer.SIZE){throw new IllegalArgumentException();}
        if(start < 0 || start >= 64){throw new IndexOutOfBoundsException();}
        if(start + size > Long.SIZE){throw new IndexOutOfBoundsException();}

        value <<= Long.SIZE - (size + start);
        value >>>= (Long.SIZE - (size + start)) + start ;

        if(value < 0) {
            value = ~value + 1;
        }
        return (int) value;
    }

    public static boolean testBit(long value, int index){
        Objects.checkIndex( index, Long.SIZE); //méthode recommandée

        value >>>= index;

        return (value & 1) == 1;
    }
}



//utilise checkArguments pour throw des illegalargumentsexceptions, elle sert a ca la classe + faut verifier que start est pas negatif aussi je pense
//Preconditions.checkArgument(size > 0 || size < Integer.SIZE || start >= 0);
//Objects.checkFromIndexSize(start, size, Long.SIZE); //nvl mùethode recommandée
//je pense que c'est faux, ils veulent qu'on extrait qqc de positif pas qu on change le long en positif avant de l extraire, c est pas la meme chose + vu qu on
// extrait un int de 31 bits max ilt peut pas etre negatif de base donc pas besoin de s'occuper de ca ( le 32 bits sera tjr 0)

// parenthèse au mauvais endroit + mauvais signe de décalalge dans la deuxième expression + jsp pk rajouter deux fois start à la fin
