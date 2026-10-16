package ch.epfl.javions.aircraft;

import java.util.Objects;

/**
 * Collecte les données fixes d'un aéronef.
 *
 * @param registration immatriculation
 * @param typeDesignator indicateur de type
 * @param model modèle
 * @param description description
 * @param wakeTurbulenceCategory catégorie de turbulence
 */
public record AircraftData(AircraftRegistration registration,
                           AircraftTypeDesignator typeDesignator,
                           String model,
                           AircraftDescription description,
                           WakeTurbulenceCategory wakeTurbulenceCategory) {
    /**
     * constructeur public
     *
     * @param registration immatriculation
     * @param typeDesignator indicateur de type
     * @param model modèle
     * @param description description
     * @param wakeTurbulenceCategory catégorie de turbulence
     */
    public AircraftData{
        Objects.requireNonNull(registration);
        Objects.requireNonNull(typeDesignator);
        Objects.requireNonNull(model);
        Objects.requireNonNull(description);
        Objects.requireNonNull(wakeTurbulenceCategory);

    }
}
