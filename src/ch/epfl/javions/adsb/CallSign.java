package ch.epfl.javions.adsb;

import ch.epfl.javions.Preconditions;

import java.util.regex.Pattern;

/**
 * Représente ce que l'on nomme l'indicatif (call sign) d'un aéronef
 *
 * @param string chaîne contenant la représentation textuelle de l'indicatif d'un aéronef
 */
public record CallSign(String string) {

    /**
     * Expression régulière
     */
    private static Pattern pattern = Pattern.compile("[A-Z0-9 ]{0,8}");

    /**
     * constructeur public
     *
     * @param string chaîne contenant la représentation textuelle de l'indicatif d'un aéronef
     */
    public CallSign {
        Preconditions.checkArgument(pattern.matcher(string).matches());
    }
}
