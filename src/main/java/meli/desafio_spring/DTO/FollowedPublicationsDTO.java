package meli.desafio_spring.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meli.desafio_spring.Entities.PublicationBase;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"userId","posts"})
public class FollowedPublicationsDTO {
    private int userId;
    private List<PublicationBase> posts;
}
