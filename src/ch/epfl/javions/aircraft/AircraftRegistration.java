package ch.epfl.javions.aircraft;

import ch.epfl.javions.Preconditions;

import java.util.regex.Pattern;

/**
 * Représente une immatriculation
 *
 * @param string chaîne contenant la représentation textuelle de l'immatriculation
 */
public record AircraftRegistration(String string) {

    /**
     * Expression régulière
     */
    private static Pattern pattern = Pattern.compile("[A-Z0-9 .?/_+-]+");

    /**
     * constructeur public
     *
     * @param string chaîne contenant la représentation textuelle de l'immatriculation
     */
    public AircraftRegistration {
        Preconditions.checkArgument( pattern.matcher(string).matches());
    }
}
