package ch.epfl.javions;

import java.util.HexFormat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Crc24Test {

    @Test
    void crc24WorksOnDifferentStrings() {

        Crc24 crc24 = new Crc24(Crc24.GENERATOR);
        String mS = "8D392AE499107FB5C00439";
        String cS = "035DB8";
        int c = Integer.parseInt(cS, 16); // == 0x035DB8

        byte[] mAndC = HexFormat.of().parseHex(mS + cS);
        assertEquals(0, crc24.crc(mAndC));

        byte[] mOnly = HexFormat.of().parseHex(mS);
        assertEquals(c, crc24.crc(mOnly));

        Crc24 crc24_2 = new Crc24(Crc24.GENERATOR);
        String mS_2 = "8D4D2286EA428867291C08";
        String cS_2 = "EE2EC6";
        int c_2 = Integer.parseInt(cS_2, 16); // == 0x035DB8

        byte[] mAndC_2 = HexFormat.of().parseHex(mS_2 + cS_2);
        assertEquals(0, crc24_2.crc(mAndC_2));

        byte[] mOnly_2 = HexFormat.of().parseHex(mS_2);
        assertEquals(c_2, crc24_2.crc(mOnly_2));
    }
}
