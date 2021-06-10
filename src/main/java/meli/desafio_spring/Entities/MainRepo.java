package meli.desafio_spring.Entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

    public void writeFile(File file, Object[] list) throws IOException {
        var objectMapper = new ObjectMapper();
        objectMapper.writeValue(file,list);
    }

}
