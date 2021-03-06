package meli.desafio_spring.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meli.desafio_spring.Entities.User;
import meli.desafio_spring.Entities.UserMinified;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"userId","userName","followers"})
public class FollowersListDTO extends UserMinified {
    private List<UserMinified> followers;

    public FollowersListDTO(User user) {
        setUserId(user.getUserId());
        setUserName(user.getUserName());
        setFollowers(user.getFollowers());
    }
}
