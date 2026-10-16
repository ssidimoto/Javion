package ch.epfl.javions.aircraft;

/**
 * Représente la catégorie de turbulence de sillage d'un aéronef.
 */
public enum WakeTurbulenceCategory {
    LIGHT,
    MEDIUM,
    HEAVY,
    UNKNOWN;

    /**
     *  Convertis les valeurs textuelles de la base de données en éléments du type énuméré
     *
     * @param s valeur textuelle
     * @return élément du type énuméré
     */
    public static WakeTurbulenceCategory of(String s){
        if (s.equals("L")) return LIGHT;
        if (s.equals("M")) return MEDIUM;
        if (s.equals("H")) return HEAVY;
        return UNKNOWN;
    }
}
