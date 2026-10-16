package ch.epfl.javions;

/**
 * Permet de projeter des coordonnées géographiques selon la projection WebMercator
 */
public class WebMercator {
    /**
     * Un constructeur privé
     */
    private WebMercator() {
    };

    /**
     * Retourne la coordonnée x correspondant à la longitude donnée au niveau de zoom donné
     *
     * @param zoomLevel zoom
     * @param longitude longitude
     * @return coordonnée x
     */
    public static double x(int zoomLevel, double longitude){
        return Math.scalb( Units.convertTo(longitude, Units.Angle.TURN) + 0.5, 8 + zoomLevel);
    }

    /**
     * Retourne la coordonnée x correspondant à la longitude donnée au niveau de zoom donné
     *
     * @param zoomLevel zoom
     * @param latitude latitude
     * @return coordonnée y
     */
    public static double y(int zoomLevel, double latitude) {
        return Math.scalb( -Units.convertTo( Math2.asinh( Math.tan(latitude)), Units.Angle.TURN) + 0.5, 8 + zoomLevel);
    }
}
