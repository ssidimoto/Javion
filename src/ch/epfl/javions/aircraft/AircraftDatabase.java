package ch.epfl.javions.aircraft;
import java.io.*;

import java.lang.invoke.TypeDescriptor;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.util.Objects;
import java.util.zip.ZipFile;

 /**
  * classe qui gère l'extraction des informations d'une base de donnée
  */
public class AircraftDatabase {

    /**
     * Nom du fichier de la base de donnée
     */
    String fileName;

    /**
     * @param fileName Nom de la base de donnée
     */
    public AircraftDatabase(String fileName) {
        this.fileName = fileName;
        Objects.requireNonNull(fileName);
    }

    /**
     *Methode qui permet de récupérer les informations concernant un avion, à partir de son ICAO
     * @param adress adresse Icao de l'avion dont on veut connaître ses charactèristiques
     * @return Le string avec toutes le informations de l'avion
     * @throws IOException
     */
    public AircraftData get(IcaoAddress adress) throws IOException {

        String dataBaseZipFileName = getClass().getResource(fileName).getFile();
        dataBaseZipFileName = URLDecoder.decode(dataBaseZipFileName, StandardCharsets.UTF_8);
        String aircraftFile = adress.string().substring(4, 6);
        try (ZipFile z = new ZipFile(dataBaseZipFileName);
             InputStream stream = z.getInputStream(z.getEntry(aircraftFile + ".csv"));
             Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String l = "";
            while ((l = bufferedReader.readLine()) != null && (adress.string().compareTo(l.substring(0, 6)) >= 0)) {
                if (l.startsWith(adress.string())) {
                    String[] informations = l.split(",");
                    return new AircraftData(new AircraftRegistration(informations[1]) ,new AircraftTypeDesignator( informations[2]),
                            informations[3], new AircraftDescription(informations[4]), WakeTurbulenceCategory.of(informations[5]));
                }
            }
            return null;
        }
    }
}