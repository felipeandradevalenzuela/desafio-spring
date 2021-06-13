package meli.desafio_spring.Services.User;

import meli.desafio_spring.DTO.FollowedListDTO;
import meli.desafio_spring.DTO.FollowersCountDTO;
import meli.desafio_spring.DTO.FollowersListDTO;
import meli.desafio_spring.Entities.User;
import meli.desafio_spring.Entities.UserMinified;
import meli.desafio_spring.Repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserRepository list;

    /**
     * Metodo para obtener el listado de usuarios que sigue un determinado usuario
     * @param userId
     * @param order
     * @return
     */
    @Override
    public FollowedListDTO getFollowedList(int userId, String order) {
        User user = list.getUser(userId);
        var resp = new FollowedListDTO(user);
        sortName(resp,order);
        return resp;
    }

    /**
     * Metodo para obtener el conteo de los usuarios que siguen a un determinado usuario
     * @param userId
     * @return
     */
    @Override
    public FollowersCountDTO getFollowersCount(int userId) {
        User user = list.getUser(userId);
        return new FollowersCountDTO(user);
    }

    /**
     * Metodo para obtener el listado de usuarios que siguen un determinado usuario
     * @param userId
     * @param order
     * @return
     */
    @Override
    public FollowersListDTO getFollowersList(int userId, String order) {
        User user = list.getUser(userId);
        FollowersListDTO resp = new FollowersListDTO(user);
        sortName(resp,order);
        return resp;
    }

    @Override
    public ResponseEntity<Object> follow(int userId, int userIdToFollow) throws IOException {
        return list.followUser(userId,userIdToFollow);
    }

    @Override
    public ResponseEntity<Object> unfollow(int userId, int userIdToFollow) throws IOException {
        return list.unfollowUser(userId,userIdToFollow);
    }

    @Override
    public ResponseEntity<Object> newUser(String userName) throws IOException {
        return list.newUser(userName);
    }

    /**
     * Metodos de ordenamiento para los distintos DTO (estos estan sobrecargados)
     * @param list
     * @param order
     */
    public void sortName(FollowedListDTO list, String order){
        switch (order){
            case "name_asc":
                list.getFolloweds().sort(Comparator.comparing(UserMinified::getUserName,String.CASE_INSENSITIVE_ORDER));
                break;
            case "name_desc":
                list.getFolloweds().sort(Comparator.comparing(UserMinified::getUserName,String.CASE_INSENSITIVE_ORDER).reversed());
                break;
            default:
        }
    }
    public void sortName(FollowersListDTO list, String order){
        switch (order){
            case "name_asc":
                list.getFollowers().sort(Comparator.comparing(UserMinified::getUserName,String.CASE_INSENSITIVE_ORDER));
                break;
            case "name_desc":
                list.getFollowers().sort(Comparator.comparing(UserMinified::getUserName,String.CASE_INSENSITIVE_ORDER).reversed());
                break;
            default:
        }
    }
}
