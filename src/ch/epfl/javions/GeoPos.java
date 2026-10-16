package ch.epfl.javions;

/**
 * Classe qui gère la position d'un objet sur une carte du monde
 *
 * @param longitudeT32 position pour situer un objet sur une carte du monde
 * @param latitudeT32  position pour situer un objet sur une carte du monde
 */
public record GeoPos(int longitudeT32, int latitudeT32) {

    /**
     * Initialise les attributs de record GeoPos
     * @param longitudeT32 longitude en turn
     * @param latitudeT32 latitude en turn
     */
    public GeoPos {
        Preconditions.checkArgument(isValidLatitudeT32(latitudeT32));
    }

    /**
     *  Retourne vrai si la valeur passée, interprétée comme une latitude exprimée en t32, est valide, c.-à-d.
     *  comprise entre -2^30 (inclus, et qui correspond à -90°) et 2^30 (inclus, et qui correspond à +90°).
     *
     * @param latitudeT32 latitude
     * @return True/False
     */
    public static boolean isValidLatitudeT32(int latitudeT32){
        return latitudeT32 >= -Math.pow(2, 30) && latitudeT32 <= Math.pow(2, 30);
    }

    /**
     * Retourne la longitude en radian
     * @return double
     */
    public double longitude(){
        return Units.convertFrom(longitudeT32, Units.Angle.T32);
    }

    /**
     * Retourne la latitude en radian
     *
     * @return double
     */
    public double latitude(){
        return Units.convertFrom(latitudeT32, Units.Angle.T32);
    }

    /**
     * Retourne une représentation textuelle de la position dans laquelle la
     * longitude et la latitude sont données dans cet ordre, en degrés
     *
     * @return Print sur le terminal
     */
    @Override
    public String toString(){
        return "(" + (Units.convert(longitudeT32, Units.Angle.T32, Units.Angle.DEGREE) + "°, "
                + Units.convert(longitudeT32, Units.Angle.T32, Units.Angle.DEGREE) + "°)");
    }
}
