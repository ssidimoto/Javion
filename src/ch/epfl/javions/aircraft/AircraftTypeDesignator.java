package ch.epfl.javions.aircraft;

import ch.epfl.javions.Preconditions;

import java.util.regex.Pattern;

/**
 * Représente un indicateur de type
 *
 * @param string chaîne contenant la représentation textuelle de l'indicateur de type
 */
public record AircraftTypeDesignator(String string) {

    /**
     * Expression régulière
     */
    private static Pattern pattern = Pattern.compile("[A-Z0-9]{2,4}");

    /**
     * Constructeur public
     *
     * @param string chaîne contenant la représentation textuelle de l'indicateur de type
     */
    public AircraftTypeDesignator {
        if (!string.isEmpty()) Preconditions.checkArgument(pattern.matcher(string).matches());
    }
}
