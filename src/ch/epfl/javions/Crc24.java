package ch.epfl.javions;

/**
 * Représente un calculateur de CRC de 24 bits
 */
public final class Crc24 {

    /**
     * Les 24 bits de poids faible du générateur utilisé pour calculer le CRC24 des messages ADS-B,
     */
    public final static int GENERATOR = 0xFFF409;
    /**
     * table permettant le caclul du crc24
     */
    public int[] table;


    /**
     * Constructeur public
     *
     * @param generator
     */
    public Crc24(int generator) {
        table = buildTable(generator);
    }

    /**
     * Retourne le CRC24 du tableau donné
     *
     * @param bytes tableau d'octets donné
     * @return le crc24
     */
    public int crc(byte[] bytes) {
        long crc24 = 0;
        for (int i = 0; i < bytes.length; i++) {
            crc24= ((crc24 << 8) | Byte.toUnsignedInt(bytes[i])) ^ table[Bits.extractUInt(crc24, 16, 8)];
        }
        for ( int i =0; i < 3; i++){
            crc24= (crc24 << 8)  ^ table[Bits.extractUInt(crc24, 16, 8)];
        }
        return Bits.extractUInt(crc24, 0, 24);

    }


    /**
     * Retourne le CRC24 du tableau donné en utilisant le generateur passé en argument
     *
     * @param generator générateur
     * @param bytes tableau d'octets
     * @return le CRC24
     */
    private static int crc_bitwise_1(int generator, byte[] bytes) {
        long crc24 = 0;
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 7; j >= 0; j--) {
                crc24 = (crc24 << 1) | Bits.extractUInt(bytes[i], j, 1);
                if (Bits.extractUInt(crc24, 24, 1) == 1) {
                    crc24 = crc24 ^ generator;
                }
            }
        }
        for ( int i =0; i < 24; i++){
            crc24 = (crc24 << 1);
            if (Bits.extractUInt(crc24, 24, 1) == 1) {
                crc24 = crc24 ^ generator;
            }
        }
        return Bits.extractUInt(crc24, 0, 24);

    }


    private static int crc_bitwise_2(int generator, byte[] bytes) {
        long crc24 = 0;
        int G = Bits.extractUInt(generator, 0, 24);
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 7; j >= 0; j--) {
                if (Bits.extractUInt(crc24, 23, 1) == 1){
                    crc24 = ((crc24 << 1) | Bits.extractUInt(bytes[i], j, 1)) ^ G;
                }
                else crc24 = (crc24 << 1) | Bits.extractUInt(bytes[i], j, 1);
            }
        }
        for ( int i =0; i < 24; i++) {
            if (Bits.extractUInt(crc24, 23, 1) == 1){
                crc24 = (crc24 << 1)  ^ G;
            }
            else crc24 = (crc24 << 1);
        }
        return Bits.extractUInt(crc24, 0, 24);
    }


    private static int crc_bitwise_3(int generator, byte[] bytes){
        int[] table = new int[]{0, Bits.extractUInt(generator, 0, 24) };
        long crc24 = 0;
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 7; j >= 0; j--) {
                crc24 = ((crc24 << 1) | Bits.extractUInt(bytes[i], j, 1)) ^ table[Bits.extractUInt(crc24, 23, 1)];
            }
        }
        for ( int i =0; i < 24; i++) {
            crc24 = (crc24 << 1)  ^ table[Bits.extractUInt(crc24, 23, 1)];;
        }
        return Bits.extractUInt(crc24, 0, 24);

    }

    private int[] buildTable(int generator){
        int[] table = new int[256];
        for (int i = 0; i<256; i++){
            table[i] =   crc_bitwise_3(generator, new byte[]{(byte)i} );
        }
        return table;
    }

}