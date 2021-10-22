package cl.ucn.disc.dsm.slobos.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

/**
 * Main class to scrappe the Directorio telefonico of UCN
 *
 *  @author Sebastian Lobos Aravena

 */
@Slf4j
public class TheMain {

    /**
     *
     * @param args
     * @throws IOException
     */
 public static void main(String[] args) throws IOException {
     log.debug("Starting the Scrapping ..");

     //connect and get the Document
     Document doc = Jsoup
             .connect("http://admision01.ucn.cl/directoriotelefonicoemail/fichaGenerica/?cod=142")
             .get();

     String nombre=doc.getElementById("lblNombre").text();
    String cargo=doc.getElementById("lblCargo").text();

    log.debug("Nombre: {}, Cargo :{} ", nombre,cargo);
     log.debug("Done.");
 }
}