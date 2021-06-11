package meli.desafio_spring.Entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"userId","userName","followed","followers"})
public class User extends UserMinified{
    private boolean isBuyer;
    private boolean isSeller;
    private List<UserMinified> followed;
    private List<UserMinified> followers;

    public User(int id, String name){
        setUserId(id);
        setUserName(name);
        setBuyer(true);
        setSeller(false);
        setFollowed(new ArrayList<>());
        setFollowers(new ArrayList<>());
    }

}
