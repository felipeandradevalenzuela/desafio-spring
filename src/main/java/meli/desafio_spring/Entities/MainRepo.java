package meli.desafio_spring.Entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

@Repository
@Data
@AllArgsConstructor
public class MainRepo {

    public void createFile(Path newFilePath){
        try {
            if(!Files.exists(newFilePath)){
                Files.createFile(newFilePath);
            }
        } catch (IOException e) {
            System.out.println("File Already Exist \n" + e);
        }
    }

    public Object readFile(File file) {
        var objectMapper = new ObjectMapper();
        Object list = new Object();

        if(file.length() == 0){
            System.out.println("No errors, and file empty");
        } else {
            try {
                list = objectMapper.readValue(file, ArrayList.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

}
