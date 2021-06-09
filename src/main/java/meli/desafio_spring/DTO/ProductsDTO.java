package meli.desafio_spring.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meli.desafio_spring.Entities.PublicationPromo;
import meli.desafio_spring.Entities.UserMinified;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO extends UserMinified {
    private List<PublicationPromo> post;
}
