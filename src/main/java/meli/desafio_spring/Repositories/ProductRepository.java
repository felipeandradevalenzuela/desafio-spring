package meli.desafio_spring.Repositories;

import meli.desafio_spring.Entities.MainRepo;
import meli.desafio_spring.Entities.Publication;
import meli.desafio_spring.Entities.PublicationPromo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
public class ProductRepository extends MainRepo implements IProductRepository {

    private Path newFilePath = Paths.get("src/main/resources/PostList.json");
    private File file = new File("src/main/resources/PostList.json");
    private Object list = super.readFile(file);

    public void createFile(){
        super.createFile(newFilePath);
    }


    @Override
    public ResponseEntity<Object> addNewPost(Publication publication) {
        createFile();
        return null;
    }

    @Override
    public ResponseEntity<Object> addNewPromoPost(PublicationPromo publicationPromo) {
        createFile();
        return null;
    }

    @Override
    public Object getList(){
        createFile();
        return list;
    }
}
