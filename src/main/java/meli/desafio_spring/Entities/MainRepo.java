package meli.desafio_spring.Entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
@Data
@AllArgsConstructor
public class MainRepo {

    /**
     * Metodo para crear un archivo a partir de un Path brindado
     * @param newFilePath
     */
    public void createFile(Path newFilePath){
        try {
            if(!Files.exists(newFilePath)){
                Files.createFile(newFilePath);
            }
        } catch (IOException e) {
            System.out.println("File Already Exist \n" + e);
        }
    }

    /**
     * Metodo para escribir un archivo de tipo List<User>
     * @param file
     * @param list
     * @throws IOException
     */
    public void writeFile(File file, List<User> list) throws IOException {
        var objectMapper = new ObjectMapper();
        objectMapper.writeValue(file,list);
    }

    /**
     * Metodo para escribir un archivo de tipo HashMap<Integer, List<PublicationPromo>
     * @param file
     * @param list
     * @throws IOException
     */
    public void writeFile(File file, HashMap<Integer, List<PublicationPromo>> list) throws IOException {
        var objectMapper = new ObjectMapper();
        objectMapper.writeValue(file,list);
    }

    /**
     * Agregar o quitar días a la fecha actual
     * @param dias
     * @return
     */
    public Date convertDate(int dias){
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_YEAR, dias);
        Date newDate = today.getTime();
        return newDate;
    }

}
