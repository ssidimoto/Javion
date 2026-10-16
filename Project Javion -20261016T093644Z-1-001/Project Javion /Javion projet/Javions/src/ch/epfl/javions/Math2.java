package ch.epfl.javions;



public final class Math2 {
    private Math2() {
    };

    public static int clamp(int min, int v, int max){
        Preconditions.checkArgument(min <= max);
        if (v <= min) return min;
        if (v >= max) return max;
        return v;
    }

<<<<<<< HEAD
=======
    /**
     * retourne le sinus hyperbolique réciproque de son argument
     *
     * @param x argument
     * @return le sinus hyperbolique
     */
>>>>>>> aa9fb40e2d6e00411a1c47a2dcad2f6e177456d8
    public static double asinh(double x){
        return  Math.log(x + Math.sqrt( 1+ x*x) );
    }


}
