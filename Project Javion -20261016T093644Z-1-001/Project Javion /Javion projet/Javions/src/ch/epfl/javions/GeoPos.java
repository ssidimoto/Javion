package ch.epfl.javions;

import javax.xml.validation.Validator;

public record GeoPos(int longitudeT32, int latitudeT32) {

    public GeoPos {
        if(! isValidLatitudeT32(latitudeT32)){
            throw new IllegalArgumentException();
        }
    }
    public static boolean isValidLatitudeT32(int latitudeT32){
        return latitudeT32 >= -Math.pow(2, 30) && latitudeT32 <= Math.pow(2, 30);
    }

    public double longitude(){
        return Units.convertFrom(longitudeT32, Units.Angle.T32);
    }

    public double latitude(){
        return Units.convertFrom(latitudeT32, Units.Angle.T32);
    }

    // toString est appelé automatiquement lorsque on utilise system.out.ln, d ou l'exemple de la feuille (3.7)
    // la fonction doit retourner un String, pas un void en imprimant directement
    @Override
    public String toString(){
        return "(" + (Units.convert(longitudeT32, Units.Angle.T32, Units.Angle.DEGREE) + "°, "
                + Units.convert(longitudeT32, Units.Angle.T32, Units.Angle.DEGREE) + "°)");
    }
}
