package ch.epfl.javions;

/**
 * classe qui gère les différentes unités de vitesse, d'angle et de position, et les conversions d'une unité à l'autre
 */
public class Units {

    /**
     * Constructeur privé
     */
    private Units(){}

    /**
     * Cste (préfix centi)
     */
    public static final double CENTI = 1e-2;

    /**
     * Cste (préfix Kilo)
     */
    public static final int KILO = 1000;

    /**
     * Classe qui gère les unités d'angles
     */

    public static class Angle{

        /**
         * Constructeur privé
         */
        private Angle(){}

        /**
         * Angle en radian
         */
        public static final double RADIAN = 1;

        /**
         * Angle en turn
         */
        public static final double TURN = RADIAN * 2 * Math.PI;

        /**
         * Angle en degré
         */
        public static final double DEGREE = TURN / 360;

        /**
         * Angle en T32
         */
        public static final double T32 = Math.scalb(TURN, -32);

    }

    /**
     * Classe qui gère les unités de distances
     */

    public static class Length{
        /**
         * Constructeur privé
         */
        private Length(){}

        /**
         * Unité métre
         */
        public static final double METER = 1;
        /**
         *Unité métre
         */
        public static final double CENTIMETER = METER * CENTI;
        /**
         *Unité centimètre
         */
        public static final double KILOMETER = METER * KILO;
        /**
         *Unité kilomètre
         */
        public static final double INCH = 2.54 * CENTIMETER;
        /**
         *Unité inch
         */
        public static  final double FOOT = 12 * INCH;
        /**
         *Unité foot
         */
        public static final double NAUTICAL_MILE = 1852 * METER;


    }

    /**
     * classe qui gère les unités de temps
     */
    public static class Time{
        /**
         *Constructeur privé
         */
        private Time(){
        }

        /**
         *Unité seconde
         */
        public static final double SECOND = 1;
        /**
         *Unité minute
         */
        public static final double MINUTE = 60*SECOND;
        /**
         *Unité heure
         */
        public static final double HOUR = 60*MINUTE;
    }

    /**
     * classe qui gère les unités de temps
     */

    public static class Speed{
        /**
         *Constructeur privé
         */
        private Speed (){}
        /**
         *unité
         */

        public static final double METER_PER_SECOND = 1;
        /**
         *unité m/s
         */
        public static final double KNOT = Length.NAUTICAL_MILE / Time.HOUR ;
        /**
         *unité knot/hr
         */
        public static final double KILOMETER_PER_HOUR = Length.KILOMETER / Time.HOUR;
    }

    /**
     * Convertit la valeur donnée, exprimée dans l'unité fromUnit, en l'unité toUnit,
     *
     * @param value valeur à convertir
     * @param fromUnit unité de départ
     * @param toUnit unité d'arrivée
     * @return valeur convertie en l'unité d'arrivée
     */
    public static double convert(double value, double fromUnit, double toUnit){
        return (fromUnit/toUnit) * value;
    }

    /**
     * Convertir la valeur de l'unité de départ, en l'unité de base
     * @param value valeur é convertir
     * @param fromUnit unité de départ
     * @return unité convertie en l'unité de départ
     */

    public static double convertFrom(double value, double fromUnit){
        return value * fromUnit;
    }

    /**
     * Convertir la valeur de l'unité de base, en l'unité d'arrivée
     * @param value valeur é convertir
     * @param toUnit unité d'arrivée
     * @return valeur  convertie en l'unité d'arrivée
     */

    public static double convertTo(double value, double toUnit){
        return value / toUnit;
    }
}
