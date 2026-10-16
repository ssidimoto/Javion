package ch.epfl.javions;


/**
 * Offre des méthodes statiques permettant d'effectuer certains calculs mathématiques
 *
 * @author Edouard Fousson (ug6Coi2l)
 * @author Anas Sidi Mohamed (Vouboh4v)
 */
public final class Math2 {
    /**
     * constructeur privé
     */
    private Math2() {
    };

    /**
     * Limite la valeur v à l'intervalle allant de min à max, en retournant min si v est inférieure à min, max si v est supérieure à max, et v sinon
     *
     * @param min minimum
     * @param v valeur à tester
     * @param max maximum
     * @return min si v est inférieure à min, max si v est supérieure à max, et v sinon
     */
    public static int clamp(int min, int v, int max){
        Preconditions.checkArgument(min <= max);
        if (v <= min) return min;
        if (v >= max) return max;
        return v;
    }

    /**
     * Retourne le sinus hyperbolique réciproque de son argument
     *
     * @param x argument
     * @return le sinus hyperbolique
     */
    public static double asinh(double x){
        return  Math.log(x + Math.sqrt( 1+ x*x) );
    }


}
