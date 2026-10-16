package ch.epfl.javions;

public class Units {
    private Units(){}

    public static final double CENTI = 1e-2;
    public static final int KILO = 1000;

    public static class Angle{
        private Angle(){}

        public static final double RADIAN = 1;
        public static final double TURN = RADIAN * 2 * Math.PI;
        public static final double DEGREE = TURN / 360;
        public static final double T32 = Math.scalb(TURN, -32);

    }
    public static class Length{
        private Length(){}
        public static final double METER = 1;
        public static final double CENTIMETER = METER * CENTI;
        public static final double KILOMETER = METER * KILO;
        public static final double INCH = 2.54 * CENTIMETER;
        public static  final double FOOT = 12 * INCH;
        public static final double NAUTICAL_MILE = 1852 * METER;

    }

    public static class Time{
        private Time(){
        }
        public static final double SECOND = 1;
        public static final double MINUTE = 60*SECOND;
        public static final double HOUR = 60*MINUTE;
    }

    public static class Speed{
        private Speed (){}

        public static final double METER_PER_SECOND = 1;
        public static final double KNOT = Length.NAUTICAL_MILE / Time.HOUR ;
        public static final double KILOMETER_PER_HOUR = Length.KILOMETER / Time.HOUR;
    }

    public static double convert(double value, double fromUnit, double toUnit){
        return (fromUnit/toUnit) * value;
    }

    public static double convertFrom(double value, double fromUnit){
        return value * fromUnit;
    }

    public static double convertTo(double value, double toUnit){
        return value / toUnit;
    }
}
