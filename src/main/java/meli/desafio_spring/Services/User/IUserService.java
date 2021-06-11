package meli.desafio_spring.Services.User;

import meli.desafio_spring.DTO.FollowedListDTO;
import meli.desafio_spring.DTO.FollowersCountDTO;
import meli.desafio_spring.DTO.FollowersListDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface IUserService {

    FollowedListDTO getFollowedList(int userId, String order);
    FollowersCountDTO getFollowersCount(int userId);
    FollowersListDTO getFollowersList(int userId, String order);
    ResponseEntity<Object> follow(int userId, int userIdToFollow) throws Exception;
    ResponseEntity<Object> unfollow(int userId, int userIdToFollow) throws Exception;
    ResponseEntity<Object> newUser(String userName) throws IOException;
}
