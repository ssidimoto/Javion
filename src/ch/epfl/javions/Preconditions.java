package ch.epfl.javions;

/**
 * Facilite l'écriture de préconditions
 */
public final class Preconditions {
    /**
     *  Constructeur par défaut privé
     */
    private Preconditions() {
    }

    /**
     * lève l'exception IllegalArgumentException si son argument est faux
     *
     * @param shouldBeTrue argument
     * @throws IllegalArgumentException si l'argument ets faux
     */
    public static void checkArgument(boolean shouldBeTrue) {
        if ( !shouldBeTrue) throw new IllegalArgumentException();
    }
}
