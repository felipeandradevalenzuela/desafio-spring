package meli.desafio_spring.Controllers;

import meli.desafio_spring.DTO.FollowedListDTO;
import meli.desafio_spring.DTO.FollowersCountDTO;
import meli.desafio_spring.DTO.FollowersListDTO;
import meli.desafio_spring.Services.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class UsersController {

    @Autowired
    private IUserService userService;

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<Object> followUser(@PathVariable int userId, @PathVariable int userIdToFollow) throws Exception {
        return userService.follow(userId,userIdToFollow);
    }

    @GetMapping("/users/{userId}/followers/count")
    public FollowersCountDTO getFollowersCount(@PathVariable int userId){
        return userService.getFollowersCount(userId);
    }

    @GetMapping("/users/{userId}/followers/list")
    public FollowersListDTO getFollowersList(@PathVariable int userId, @RequestParam(defaultValue = "none") String order){
        return userService.getFollowersList(userId,order);
    }

    @GetMapping("/users/{userId}/followed/list")
    public FollowedListDTO getFollowedList(@PathVariable int userId, @RequestParam(defaultValue = "none") String order){
        return userService.getFollowedList(userId,order);
    }

    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<Object> unfollowUser(@PathVariable int userId, @PathVariable int userIdToUnfollow) throws Exception {
        return userService.unfollow(userId,userIdToUnfollow);
    }

    @PostMapping("/user/add/{userName}")
    public ResponseEntity<Object> newUser(@PathVariable String userName) throws IOException {
        return userService.newUser(userName);
    }

}
