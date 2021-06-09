package meli.desafio_spring.Controllers;

import meli.desafio_spring.DTO.FollowedDTO;
import meli.desafio_spring.DTO.FollowersCountDTO;
import meli.desafio_spring.DTO.FollowersListDTO;
import meli.desafio_spring.Services.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

    @Autowired
    private IUserService userService;

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<Object> followUser(@PathVariable int userId, @PathVariable int userIdToFollow){
        userService.getFollowersCount(userId);
        return new ResponseEntity<>("User ID: "+ userId + ", User ID to follow "+userIdToFollow, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/followers/count")
    public FollowersCountDTO getFollowersCount(@PathVariable int userId){
        return null;
    }

    @GetMapping("/users/{userId}/followers/list")
    public FollowersListDTO getFollowersList(@PathVariable int userId, @RequestParam String order){
        return null;
    }

    @GetMapping("/users/{userId}/followed/list")
    public FollowedDTO getFollowedList(@PathVariable int userId){
        return null;
    }

    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public void unfollowUser(@PathVariable int userId, @PathVariable int userIdToUnfollow){

    }

}
