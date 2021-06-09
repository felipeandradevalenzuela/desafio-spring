package meli.desafio_spring.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends UserMinified{
    private boolean isBuyer;
    private boolean isSeller;
    private List<UserMinified> followed;
    private List<UserMinified> followers;
}
