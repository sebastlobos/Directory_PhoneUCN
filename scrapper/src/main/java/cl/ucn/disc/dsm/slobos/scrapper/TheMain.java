package cl.ucn.disc.dsm.slobos.scrapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.core.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Main class to scrappe the Directorio telefonico of UCN
 *
 *  @author Sebastian Lobos Aravena

 */
@Slf4j
public class TheMain {
    /**
     * The Gson Parser.
     */
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     *
     * @param args to use.
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

    // The list of Funcionario
     List<Funcionario> funcionarios = new ArrayList<>();

     //add the funcionario into the list.
     funcionarios.add(f);
     FileUtils.writeStringToFile
             (new File("funcionarios.json"),
             GSON.toJson(funcionarios),
             StandardCharsets.UTF_8);

     log.debug("Done.");
 }
}