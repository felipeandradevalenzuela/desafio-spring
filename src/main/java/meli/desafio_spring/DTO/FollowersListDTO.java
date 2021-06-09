package meli.desafio_spring.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meli.desafio_spring.Entities.User;
import meli.desafio_spring.Entities.UserMinified;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowersListDTO extends UserMinified {
    private List<UserMinified> followers;
}
