package meli.desafio_spring.Repositories;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import meli.desafio_spring.Entities.MainRepo;
import meli.desafio_spring.Entities.User;
import meli.desafio_spring.Entities.UserMinified;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserRepository extends MainRepo implements IUserRepository{

    private Path newFilePath = Paths.get("src/main/resources/UserList.json");
    private File file = new File("src/main/resources/UserList.json");
    private User[] list = readFile(file);

    public void createFile(){
        super.createFile(newFilePath);
    }
    public void writeFile() throws IOException {
       super.writeFile(file,list);
    }

    @Override
    public ResponseEntity<Object> followUser(int userId, int userIdToFollow) throws IOException {

        //buscamos en los datos persistentes, la existencia de los id de usuario
        User follower= getUser(userId);
        User followed= getUser(userIdToFollow);

        //obtenemos los listados respectivos de seguidores y seguidos
        List<UserMinified> followsList = follower.getFollowers();
        List<UserMinified> followedList = followed.getFollowed();

        //revisamos si ya es seguido por este usuario
        boolean followerExist = followedList.stream()
                .anyMatch(usr -> usr.getUserId() == userId);


        if(!followerExist && followed.isSeller()){
            UserMinified nFollower = new UserMinified(followed.getUserId(),followed.getUserName());
            UserMinified nFollowed = new UserMinified(follower.getUserId(),follower.getUserName());

            followsList.add(nFollower);
            followedList.add(nFollowed);

            followed.setFollowed(followedList);
            follower.setFollowers(followsList);
        } else {
            return new ResponseEntity<>("Existen problemas para relacionar a los usuarios", HttpStatus.BAD_REQUEST);
        }

        writeFile();
        return new ResponseEntity<>("Relación creada de manera exitosa", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> unfollowUser(int userId, int userIdToUnfollow) throws IOException {

        //buscamos en los datos persistentes, la existencia de los id de usuario
        User follower= getUser(userId);
        User followed = getUser(userIdToUnfollow);

        //obtenemos los listados respectivos de seguidores y seguidos
        List<UserMinified> followsList = follower.getFollowers();
        List<UserMinified> followedList = followed.getFollowed();

        //revisamos si ya es seguido por este usuario
        boolean followerExist = followedList.stream()
                .anyMatch(usr -> usr.getUserId() == userId);

        if(followerExist){
            UserMinified nFollower = new UserMinified(followed.getUserId(),followed.getUserName());
            UserMinified nFollowed = new UserMinified(follower.getUserId(),follower.getUserName());

            followsList.remove(nFollower);
            followedList.remove(nFollowed);

            followed.setFollowed(followedList);
            follower.setFollowers(followsList);
        } else {
            //throw new IOException("Ya existe una relación entre los usuarios");
            return new ResponseEntity<>("No existe una relación entre los usuarios", HttpStatus.BAD_REQUEST);
        }

        writeFile();
        return new ResponseEntity<>("Relación eliminada de manera exitosa", HttpStatus.OK);
    }

    @Override
    public User[] getList(){
        return readFile(file);
    }

    public User[] readFile(File file) {
        createFile();
        var objectMapper = new ObjectMapper();
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if(file.length() == 0){
            System.out.println("No errors, and file empty");
        } else {
            try {
                list = objectMapper.readValue(file, User[].class);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public User setAsSeller(int userId){
        User tmpUser = getUser(userId);
        if(!tmpUser.isSeller()){
            tmpUser.setSeller(true);
            try {
                writeFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tmpUser;
    }

    public User getUser(int userId){
        User user = Arrays.stream(getList())
                .filter(usr -> usr.getUserId() == userId)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("No existe el ID de usuario, ID: "+userId));
        return user;
    }

}
