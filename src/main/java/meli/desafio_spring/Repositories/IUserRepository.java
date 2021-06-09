package meli.desafio_spring.Repositories;

import org.springframework.http.ResponseEntity;

public interface IUserRepository {

    ResponseEntity<Object> followUser(int userId, int userIdToFollow);
    ResponseEntity<Object> unfollowUser(int userId, int userIdToUnfollow);
    Object getList();
}
