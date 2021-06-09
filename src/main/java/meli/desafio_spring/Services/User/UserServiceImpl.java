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

    @Override
    public FollowedListDTO getFollowedList(int userId, String order) {
        User user = Arrays.stream(list.getList())
                .filter(usr -> usr.getUserId() == userId)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("No existe el ID de usuario, ID: "+userId));

        FollowedListDTO resp = new FollowedListDTO();

        resp.setUserId(user.getUserId());
        resp.setUserName(user.getUserName());
        resp.setFolloweds(user.getFollowed());
        return resp;
    }

    @Override
    public FollowersCountDTO getFollowersCount(int userId) {
        User user = Arrays.stream(list.getList())
                .filter(usr -> usr.getUserId() == userId)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("No existe el ID de usuario, ID: "+userId));

        List<UserMinified> followers = user.getFollowers();
        FollowersCountDTO resp = new FollowersCountDTO();

        resp.setUserId(user.getUserId());
        resp.setUserName(user.getUserName());
        resp.setFollowers_count(followers.size());

        return resp;
    }

    @Override
    public FollowersListDTO getFollowersList(int userId, String order) {
        User user = Arrays.stream(list.getList())
                .filter(usr -> usr.getUserId() == userId)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("No existe el ID de usuario, ID: "+userId));

        FollowersListDTO resp = new FollowersListDTO();

        resp.setUserId(user.getUserId());
        resp.setUserName(user.getUserName());
        resp.setFollowers(user.getFollowers());
        return resp;
    }

    public ResponseEntity<Object> follow(int userId, int userIdToFollow) throws IOException {
        return list.followUser(userId,userIdToFollow);
    }

    public ResponseEntity<Object> unfollow(int userId, int userIdToFollow) throws IOException {
        return list.unfollowUser(userId,userIdToFollow);
    }
}
