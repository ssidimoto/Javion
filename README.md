# Javions

Projet Java réalisé dans le cadre du cours **CS-108 – Pratique de la programmation orientée-objet** (EPFL, 2023).

Javions a pour but de recevoir et décoder les messages **ADS-B** (*Automatic Dependent Surveillance–Broadcast*) émis en continu par les avions sur la fréquence 1090 MHz. Ces messages contiennent l'identité de l'appareil, sa position, son altitude, sa vitesse, etc. Le projet transforme le signal radio brut en informations exploitables, puis les associe à une base de données des aéronefs.

> **État du projet :** les classes utilitaires et la base de données des aéronefs sont fonctionnelles. La partie démodulation (`demodulation/`) est en cours d'écriture.

## Fonctionnement

Le traitement suit une chaîne en plusieurs étapes :

```
signal radio (échantillons bruts)
        │
        ▼
  demodulation/     SamplesDecoder → PowerComputer → PowerWindow
        │           (échantillons → puissance du signal → fenêtre de détection)
        ▼
  messages ADS-B    vérification de l'intégrité avec Crc24
        │
        ▼
  adsb/ + aircraft/ décodage (indicatif, position…) et recherche de l'avion
                    dans la base de données à partir de son adresse ICAO
```

## Structure du projet

```
src/ch/epfl/javions/
├── Bits.java              extraction de bits dans un long (extractUInt, testBit)
├── ByteString.java        chaîne d'octets immuable (création depuis l'hexadécimal, lecture par plage)
├── Crc24.java             calcul du CRC 24 bits utilisé par ADS-B
├── GeoPos.java            position géographique (longitude/latitude en unités T32)
├── Math2.java             fonctions mathématiques (clamp, asinh)
├── Preconditions.java     vérification des arguments (checkArgument)
├── Units.java             unités (angles, longueurs, temps, vitesses) et conversions
├── WebMercator.java       projection Web Mercator (coordonnées sur une carte selon le zoom)
├── adsb/
│   └── CallSign.java      indicatif d'appel d'un vol
├── aircraft/
│   ├── AircraftDatabase.java        lecture de la base de données (aircraft.zip)
│   ├── AircraftData.java            caractéristiques d'un avion
│   ├── IcaoAddress.java             adresse ICAO (identifiant unique de 24 bits, 6 caractères hexa)
│   ├── AircraftRegistration.java    immatriculation
│   ├── AircraftTypeDesignator.java  code du type d'appareil
│   ├── AircraftDescription.java     description (ex. L2J)
│   └── WakeTurbulenceCategory.java  catégorie de turbulence de sillage (LIGHT, MEDIUM, HEAVY…)
└── demodulation/          démodulation du signal (en cours)

src/ch/epfl/cs108/Submit.java   outil de rendu du cours
test/                           tests JUnit 5
resources/aircraft.zip          base de données des aéronefs (un fichier CSV par octet de poids faible de l'adresse ICAO)
Javions.iml                     module IntelliJ IDEA
```

Les types simples (`IcaoAddress`, `CallSign`, `AircraftRegistration`…) sont des *records* qui valident leur contenu à la construction : une valeur invalide lève une `IllegalArgumentException`.

## Prérequis

- **JDK 17** ou plus récent
- **JUnit 5 (Jupiter)** pour les tests

## Utilisation

### Avec IntelliJ IDEA (recommandé)

1. Cloner le dépôt :
   ```bash
   git clone https://github.com/ssidimoto/Javion.git
   ```
2. Dans IntelliJ : **File → Open** et choisir le dossier `Javion`. Le module `Javions.iml` configure déjà `src/` (sources), `test/` (tests) et `resources/` (ressources).
3. Configurer le SDK du projet sur le JDK 17 (**File → Project Structure → SDK**).
4. Ajouter la bibliothèque JUnit 5 si IntelliJ la signale comme manquante (survoler un `@Test` → *Add JUnit 5 to classpath*).
5. Lancer les tests : clic droit sur le dossier `test` → **Run 'All Tests'**.

### En ligne de commande

Compiler les sources :

```bash
javac -d out $(find src -name '*.java')
```

Lancer les tests avec le lanceur JUnit autonome ([junit-platform-console-standalone](https://central.sonatype.com/artifact/org.junit.platform/junit-platform-console-standalone)) :

```bash
javac -d out -cp out:junit-platform-console-standalone.jar $(find test -name '*.java')
java -jar junit-platform-console-standalone.jar -cp out:resources --scan-classpath
```

`resources/` doit être dans le classpath pour que `aircraft.zip` soit trouvé.

### Exemple : rechercher un avion

```java
import ch.epfl.javions.aircraft.*;

AircraftDatabase db = new AircraftDatabase("/aircraft.zip");
AircraftData data = db.get(new IcaoAddress("4B1814"));

if (data != null) {
    System.out.println(data.registration().string()); // HB-JDC
    System.out.println(data.model());                 // AIRBUS A-320neo
}
```

`get` renvoie `null` si l'adresse ICAO n'est pas dans la base.

### Exemple : utilitaires

```java
// Conversion d'unités
double meters = Units.convertFrom(35_000, Units.Length.FOOT);

// Position géographique en unités T32
GeoPos pos = new GeoPos(longitudeT32, latitudeT32);
double lon = pos.longitude(); // en radians

// Projection sur une carte au niveau de zoom 10
double x = WebMercator.x(10, pos.longitude());
double y = WebMercator.y(10, pos.latitude());

// Vérification CRC d'un message
Crc24 crc = new Crc24(Crc24.GENERATOR);
byte[] message = HexFormat.of().parseHex("8D392AE499107FB5C00439");
int c = crc.crc(message);
```
