package meli.desafio_spring.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meli.desafio_spring.Entities.PublicationPromo;
import meli.desafio_spring.Entities.User;
import meli.desafio_spring.Entities.UserMinified;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"userId","userName","post"})
public class ProductsDTO extends UserMinified {
    private List<PublicationPromo> post;

    public ProductsDTO(User user, List<PublicationPromo> posts) {
        setUserId(user.getUserId());
        setUserName(user.getUserName());
        setPost(posts);
    }
}
