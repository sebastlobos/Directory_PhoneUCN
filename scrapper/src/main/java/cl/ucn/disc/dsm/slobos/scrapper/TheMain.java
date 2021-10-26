package cl.ucn.disc.dsm.slobos.scrapper;

import static java.lang.Character.getType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

 public static void main(String[] args) throws IOException, InterruptedException {

//Define the type
     Type type = new TypeToken<List<Funcionario>>(){
     }.getType();

     // Load the file
    String data= FileUtils.readFileToString(new File("Funcionarios.json"), StandardCharsets.UTF_8);

     // The list of Funcionario (String -> List<Funcionario>)
     List<Funcionario> funcionarios =GSON.fromJson(data,type);

     int start=funcionarios.get(funcionarios.size()-1).getId();
     int end=30000;
     Random r= new Random();
     log.debug("Starting the scrapping from {} to {}..", start, end);

    //found the funcionario
    for(int id=start; id<=end;id++){

        //wait for..
       Thread.sleep(50 +r.nextInt(350 ));

    log.debug("Retriving funcionario id: {}", id);

    //connect and get the Document
    Document doc = Jsoup
            .connect("http://admision01.ucn.cl/directoriotelefonicoemail/fichaGenerica/?cod="+ id)
            .get();

    //Scrapping

    String nombre=doc.getElementById("lblNombre").text();
    String cargo=doc.getElementById("lblCargo").text();
    String unidad=doc.getElementById("lblUnidad").text();
    String email=doc.getElementById("lblEmail").text();
    String telefono=doc.getElementById("lblTelefono").text();
    String oficina=doc.getElementById("lblOficina").text();
    String direccion=doc.getElementById("lblDireccion").text();

    //skip if data not found
    if(nombre.length()<=1){
        log.warn("No data found fo id: {}.", id);
        continue;

    }// end if

        log.info("Funcionario id:{} founded: {}.", id, nombre);



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

        //add the funcionario into the list.
        funcionarios.add(f);



    //Save by 25.
        if(funcionarios.size() % 25 == 0){

            log.debug("Writing{} Funcionarios to file.. ", funcionarios.size());

            //Write the list of Funcionario in Json Format
            FileUtils.writeStringToFile
                    (new File("funcionarios.json"),
                            GSON.toJson(funcionarios),
                            StandardCharsets.UTF_8);

        }//End if "save by 25".
        } //End for

     log.debug("Done.");
 }




}