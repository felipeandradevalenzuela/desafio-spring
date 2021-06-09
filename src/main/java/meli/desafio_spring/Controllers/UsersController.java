package meli.desafio_spring.Controllers;

import meli.desafio_spring.DTO.FollowedListDTO;
import meli.desafio_spring.DTO.FollowersCountDTO;
import meli.desafio_spring.DTO.FollowersListDTO;
import meli.desafio_spring.Services.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

    @Autowired
    private IUserService userService;

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<Object> followUser(@PathVariable int userId, @PathVariable int userIdToFollow) throws Exception {
        var resp = userService.follow(userId,userIdToFollow);
        return resp;
    }

    @GetMapping("/users/{userId}/followers/count")
    public FollowersCountDTO getFollowersCount(@PathVariable int userId){
        var resp = userService.getFollowersCount(userId);
        return resp;
    }

    @GetMapping("/users/{userId}/followers/list")
    public FollowersListDTO getFollowersList(@PathVariable int userId, @RequestParam(defaultValue = "none") String order){
        var resp = userService.getFollowersList(userId,order);
        //FALTA DESARROLLAR EL ORDEN
        return resp;
    }

    @GetMapping("/users/{userId}/followed/list")
    public FollowedListDTO getFollowedList(@PathVariable int userId, @RequestParam(defaultValue = "none") String order){
        var resp = userService.getFollowedList(userId,order);
        //FALTA DESARROLLAR EL ORDEN
        return resp;
    }

    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<Object> unfollowUser(@PathVariable int userId, @PathVariable int userIdToUnfollow) throws Exception {
        var resp = userService.unfollow(userId,userIdToUnfollow);
        return resp;
    }

}
