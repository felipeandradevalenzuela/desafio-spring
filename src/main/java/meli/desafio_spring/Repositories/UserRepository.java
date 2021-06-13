package meli.desafio_spring.Repositories;

import com.fasterxml.jackson.core.type.TypeReference;
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
import java.util.List;

@Repository
public class UserRepository extends MainRepo implements IUserRepository{

    private Path newFilePath = Paths.get("src/main/resources/UserList.json");
    private File file = new File("src/main/resources/UserList.json");
    private List<User> list = readFile(file);

    public void createFile(){
        super.createFile(newFilePath);
    }
    public void writeFile() throws IOException {
        super.writeFile(file, list);
    }

    /**
     *  Metodo para realizar el seguimiento de un usuario a otro
     * @param userId
     * @param userIdToFollow
     * @return
     * @throws IOException
     */
    @Override
    public ResponseEntity<Object> followUser(int userId, int userIdToFollow) throws IOException {

        //buscamos en los datos persistentes, la existencia de los id de usuario
        User follower= getUser(userId);
        User followed= getUser(userIdToFollow);

        //obtenemos los listados respectivos de seguidores y seguidos
        List<UserMinified> followsList = follower.getFollowed();
        List<UserMinified> followedList = followed.getFollowers();

        //revisamos si ya es seguido por este usuario
        boolean followerExist = followedList.stream()
                .anyMatch(usr -> usr.getUserId() == userId);


        if(!followerExist && followed.isSeller()){
            UserMinified nFollower = new UserMinified(follower.getUserId(),follower.getUserName());
            UserMinified nFollowed = new UserMinified(followed.getUserId(),followed.getUserName());

            followsList.add(nFollowed);
            followedList.add(nFollower);

            followed.setFollowers(followedList);
            follower.setFollowed(followsList);
        } else {
            return new ResponseEntity<>("Existen problemas para relacionar a los usuarios", HttpStatus.BAD_REQUEST);
        }

        writeFile();
        return new ResponseEntity<>("Relación creada de manera exitosa", HttpStatus.OK);
    }

    /**
     * Metodo para quitar el seguimiento de un usuario a otro
     * @param userId
     * @param userIdToUnfollow
     * @return
     * @throws IOException
     */
    @Override
    public ResponseEntity<Object> unfollowUser(int userId, int userIdToUnfollow) throws IOException {

        //buscamos en los datos persistentes, la existencia de los id de usuario
        User follower= getUser(userId);
        User followed = getUser(userIdToUnfollow);

        //obtenemos los listados respectivos de seguidores y seguidos
        var followsList = follower.getFollowers();
        var followedList = followed.getFollowed();
        var userFollowedList = follower.getFollowed();

        //revisamos si ya es seguido por este usuario
        boolean followerExist = userFollowedList.stream()
                .anyMatch(usr -> usr.getUserId() == userIdToUnfollow);

        if(followerExist){
            UserMinified nFollower = new UserMinified(follower.getUserId(),follower.getUserName());
            UserMinified nFollowed = new UserMinified(followed.getUserId(),followed.getUserName());

            followsList.remove(nFollowed);
            followedList.remove(nFollower);

            followed.setFollowers(followedList);
            follower.setFollowed(followsList);
        } else {
            return new ResponseEntity<>("No existe una relación entre los usuarios", HttpStatus.BAD_REQUEST);
        }

        writeFile();
        return new ResponseEntity<>("Relación eliminada de manera exitosa", HttpStatus.OK);
    }

    /**
     * Metodo para obtener el listado de usuarios existentes
     * @return
     */
    @Override
    public List<User> getList(){
        return list;
    }

    /**
     * Metodo para la creación de un nuevo usuario
     * @param userName
     * @return
     * @throws IOException
     */
    public ResponseEntity<Object> newUser(String userName) throws IOException {
        var tmp = list.stream().filter(u -> u.getUserName().equals(userName)).findFirst();

        if(!tmp.isPresent()){
            User tmpUser = new User(list.size()+1,userName);

            try {
                list.add(tmpUser);
            } catch (RuntimeException e){
                throw new RuntimeException("Problemas para agregar al usuario al listado temporal.\n "+e);
            }
            writeFile();
            return new ResponseEntity<>(tmpUser,HttpStatus.OK);
        }
        return new ResponseEntity<>("El nombre de usuario: "+userName +" ya existe, y su ID es : "+tmp.get().getUserId(),HttpStatus.BAD_REQUEST);
    }

    public List<User> readFile(File file) {
        createFile();
        var objectMapper = new ObjectMapper();
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TypeReference<List<User>> userTypeRef = new TypeReference<>() {};
        if(file.length() == 0){
            System.out.println("No errors, and file empty");
        } else {
            try {
                list = objectMapper.readValue(file, userTypeRef);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    /**
     * Metodo para setear a un usuario como vendedor
     * @param userId
     * @return
     */
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

    /**
     * Metodo para obtener un usuario a partir de su id
     * @param userId
     * @return
     */
    public User getUser(int userId){
        User user = list.stream()
                .filter(usr -> usr.getUserId() == userId)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("No existe el ID de usuario, ID: "+userId));
        return user;
    }

}
