package meli.desafio_spring.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDetail {
    private int product_id;
    private String productName,type,brand,color,notes;
}
