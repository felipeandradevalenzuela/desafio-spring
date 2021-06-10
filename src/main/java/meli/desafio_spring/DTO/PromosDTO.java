package meli.desafio_spring.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meli.desafio_spring.Entities.UserMinified;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"userId","userName","promoproducts_count"})
public class PromosDTO extends UserMinified {
    private int promoproducts_count;
}
