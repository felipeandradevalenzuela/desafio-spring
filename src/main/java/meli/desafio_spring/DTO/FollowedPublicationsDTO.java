package meli.desafio_spring.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meli.desafio_spring.Entities.Publication;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowedPublicationsDTO {
    private int userId;
    private List<Publication> posts;
}
