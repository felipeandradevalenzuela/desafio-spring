package meli.desafio_spring.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationPromo extends Publication {
    private boolean hasPromo;
    private double discount;
}
