package meli.desafio_spring.Services.User;

import meli.desafio_spring.DTO.FollowedDTO;
import meli.desafio_spring.DTO.FollowersCountDTO;
import meli.desafio_spring.DTO.FollowersListDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    FollowedDTO getFollowedList(int userId);
    FollowersCountDTO getFollowersCount(int userId);
    FollowersListDTO getFollowersList(int userId);
    ResponseEntity<Object> follow(int userId, int userIdToFollow);
}
