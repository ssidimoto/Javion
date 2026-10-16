package ch.epfl.javions.aircraft;

import ch.epfl.javions.Preconditions;

import java.util.regex.Pattern;

/**
 * Représente une adresse OACI
 *
 * @param icaoAdress chaîne contenant la représentation textuelle de l'adresse OACI
 */
public record IcaoAddress(String string) {

    /**
     * Expression régulière
     */
    private static Pattern pattern = Pattern.compile("[0-9A-F]{6}");

    /**
     * Constructeur public
     *
     * @param string chaîne contenant la représentation textuelle de l'adresse OACI
     */
    public IcaoAddress{
        Preconditions.checkArgument( pattern.matcher(string).matches());
    }
}
