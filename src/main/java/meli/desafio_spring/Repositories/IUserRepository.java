package meli.desafio_spring.Repositories;

import meli.desafio_spring.Entities.User;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface IUserRepository {

    ResponseEntity<Object> followUser(int userId, int userIdToFollow) throws IOException;
    ResponseEntity<Object> unfollowUser(int userId, int userIdToUnfollow) throws IOException;
    User[] getList();
}
