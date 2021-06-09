package meli.desafio_spring.Repositories;

import meli.desafio_spring.Entities.MainRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
public class UserRepository extends MainRepo implements IUserRepository{

    private Path newFilePath = Paths.get("src/main/resources/UserList.json");
    private File file = new File("src/main/resources/UserList.json");
    private Object list = super.readFile(file);

    public void createFile(){
        super.createFile(newFilePath);
    }

    @Override
    public ResponseEntity<Object> followUser(int userId, int userIdToFollow) {
        createFile();
        //CONSIDERAR TRABAJAR CON JSON VACIOS
        if(list != null)
        {

        }
        return null;
    }

    @Override
    public ResponseEntity<Object> unfollowUser(int userId, int userIdToUnfollow) {
        createFile();
        return null;
    }

    @Override
    public Object getList(){
        createFile();
        return list;
    }
}
