package meli.desafio_spring.Services.User;

import meli.desafio_spring.DTO.FollowedDTO;
import meli.desafio_spring.DTO.FollowersCountDTO;
import meli.desafio_spring.DTO.FollowersListDTO;
import meli.desafio_spring.Entities.User;
import meli.desafio_spring.Repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{


    @Autowired
    private IUserRepository list;

    @Override
    public FollowedDTO getFollowedList(int userId) {
        return null;
    }

    @Override
    public FollowersCountDTO getFollowersCount(int userId) {
        list.getList();
        return null;
    }

    @Override
    public FollowersListDTO getFollowersList(int userId) {
        return null;
    }

    public ResponseEntity<Object> follow(int userId, int userIdToFollow){
        var data = list.getList();

        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
