package meli.desafio_spring.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meli.desafio_spring.Entities.UserMinified;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromosDTO extends UserMinified {
    private int promoproducts_count;
}
