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

     //The id.
        int id=142;


     //connect and get the Document
     Document doc = Jsoup
             .connect("http://admision01.ucn.cl/directoriotelefonicoemail/fichaGenerica/?cod=142")
             .get();

    //Scrapping

     String nombre=doc.getElementById("lblNombre").text();
     String cargo=doc.getElementById("lblCargo").text();
     String unidad=doc.getElementById("lblUnidad").text();
     String email=doc.getElementById("lblEmail").text();
     String telefono=doc.getElementById("lblTelefono").text();
     String oficina=doc.getElementById("lblOficina").text();
     String direccion=doc.getElementById("lblDireccion").text();

     //Call the constructor
     Funcionario f=Funcionario.builder()
             .id(id)
             .nombre(nombre)
             .cargo(cargo)
             .unidad(unidad)
             .email(email)
             .telefono(telefono)
             .oficina(oficina)
             .direccion(direccion)
             .build();

     Funcionario F= new Funcionario(id,nombre,cargo,unidad,email,telefono,oficina,direccion);
     log.debug("Done.");
 }
}