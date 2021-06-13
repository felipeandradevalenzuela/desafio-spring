package meli.desafio_spring.Repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import meli.desafio_spring.Entities.MainRepo;
import meli.desafio_spring.Entities.Publication;
import meli.desafio_spring.Entities.PublicationPromo;
import meli.desafio_spring.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository extends MainRepo implements IProductRepository {

    @Autowired
    private IUserRepository listUser;

    private Path newFilePath = Paths.get("src/main/resources/PostList.json");
    private File file = new File("src/main/resources/PostList.json");
    private HashMap<Integer, List<PublicationPromo>> list = readFile(file);

    public void createFile(){
        super.createFile(newFilePath);
    }
    public void writeFile() throws IOException {
        super.writeFile(file,list);
    }

    /**
     * Metodo para agregar un nuevo producto a la bd
     * @param publication
     * @return
     */
    @Override
    public ResponseEntity<Object> addNewPost(Publication publication) {
        if(list == null){
            list = new HashMap<Integer, List<PublicationPromo>>();
            List<PublicationPromo> tmpList = new ArrayList<>();
            tmpList.add(factoryPost(publication));
            list.put(publication.getUserId(), tmpList);
        }else if(list.containsKey(publication.getUserId())){
            list.get(publication.getUserId()).add(factoryPost(publication));
        } else {
            List<PublicationPromo> tmpList = new ArrayList<>();
            tmpList.add(factoryPost(publication));
            list.put(publication.getUserId(), tmpList);
        }
        try {
            writeFile();
        } catch (IOException e) {
            return new ResponseEntity<>("No fue posible agregar el producto, este contiene campos erroneos", HttpStatus.BAD_REQUEST);
        }
        listUser.setAsSeller(publication.getUserId());
        return new ResponseEntity<>("Se agrego el producto de manera correcta", HttpStatus.OK);
    }

    /**
     * Metodo para agregar un nuevo producto en promocion a la bd
     * @param publicationPromo
     * @return
     */
    @Override
    public ResponseEntity<Object> addNewPromoPost(PublicationPromo publicationPromo) {
        if(list == null){
            list = new HashMap<Integer, List<PublicationPromo>>();
            List<PublicationPromo> tmpList = new ArrayList<>();
            tmpList.add(publicationPromo);
            list.put(publicationPromo.getUserId(), tmpList);
        }else if(list.containsKey(publicationPromo.getUserId())){
            list.get(publicationPromo.getUserId()).add(publicationPromo);
        } else {
            List<PublicationPromo> tmpList = new ArrayList<>();
            tmpList.add(publicationPromo);
            list.put(publicationPromo.getUserId(), tmpList);
        }

        if(list.get(publicationPromo.getUserId()) == null){
            publicationPromo.setId_post(0);
            publicationPromo.getDetail().setProduct_id(0);
        }else{
            publicationPromo.setId_post(list.get(publicationPromo.getUserId()).size());
            publicationPromo.getDetail().setProduct_id(list.get(publicationPromo.getUserId()).size());
        }
        try {
            writeFile();
        } catch (IOException e) {
            return new ResponseEntity<>("No fue posible agregar el producto, este contiene campos erroneos", HttpStatus.BAD_REQUEST);
        }
        listUser.setAsSeller(publicationPromo.getUserId());
        return new ResponseEntity<>("Se agrego el producto de manera correcta", HttpStatus.OK);
    }

    /**
     * Metodo paraobtener el listado de publicaciones
     * @return
     */
    @Override
    public HashMap<Integer, List<PublicationPromo>> getList(){
        return list;
    }

    public HashMap<Integer, List<PublicationPromo>> readFile(File file) {
        createFile();
        var objectMapper = new ObjectMapper();
        TypeReference<HashMap<Integer,List<PublicationPromo>>> typeRef = new TypeReference<HashMap<Integer,List<PublicationPromo>>>() {};
        if(file.length() == 0){
            System.out.println("No errors, and file empty");
        } else {
            try {
                list = objectMapper.readValue(file, typeRef);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * Metodo para crear de manera dinamica los productos
     * @param publication
     * @return
     */
    public PublicationPromo factoryPost(Publication publication){
        PublicationPromo tmp = new PublicationPromo(publication);
        if(list.get(publication.getUserId()) == null){
            tmp.setId_post(0);
            tmp.getDetail().setProduct_id(0);
        }else{
            tmp.setId_post(list.get(publication.getUserId()).size());
            tmp.getDetail().setProduct_id(list.get(publication.getUserId()).size());
        }
        return tmp;
    }
}
