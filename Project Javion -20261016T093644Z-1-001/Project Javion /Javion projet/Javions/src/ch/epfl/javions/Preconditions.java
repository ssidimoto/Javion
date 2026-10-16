package ch.epfl.javions;

/**
 *
 */
public final class Preconditions {
    /**
     *  constructeur par défaut privé
     */
    private Preconditions() {
    }

    /**
     * lève l'exception IllegalArgumentException si son argument est faux
     *
     * @param shouldBeTrue argument
     * @throws
     */
    public static void checkArgument(boolean shouldBeTrue) {
        if ( !shouldBeTrue) throw new IllegalArgumentException();
    }
}
