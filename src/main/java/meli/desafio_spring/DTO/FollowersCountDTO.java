package meli.desafio_spring.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meli.desafio_spring.Entities.User;
import meli.desafio_spring.Entities.UserMinified;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"userId","userName","followers_count"})
public class FollowersCountDTO extends UserMinified {
    private int followers_count;

    public FollowersCountDTO(User user) {
        setUserId(user.getUserId());
        setUserName(user.getUserName());
        setFollowers_count(user.getFollowers().size());
    }
}
