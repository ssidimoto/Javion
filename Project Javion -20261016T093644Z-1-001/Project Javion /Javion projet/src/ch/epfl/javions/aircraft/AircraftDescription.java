package ch.epfl.javions.aircraft;

import ch.epfl.javions.Preconditions;

import java.util.regex.Pattern;

/**
 * Représente une description
 *
 * @param string chaîne contenant la représentation textuelle de la description
 */
public record AircraftDescription(String string) {

    /**
     * Expression régulière
     */
    private static Pattern pattern = Pattern.compile("[ABDGHLPRSTV-][0123468][EJPT-]");

    /**
     * constructeur public
     *
     * @param string chaîne contenant la représentation textuelle de la description
     */
    public AircraftDescription {
        if (!string.isEmpty()) Preconditions.checkArgument(pattern.matcher(string).matches());
    }
}
