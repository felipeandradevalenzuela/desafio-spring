package meli.desafio_spring.Entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"product_id","productName","type","brand","color","notes"})
public class PublicationDetail {
    private int product_id;
    private String productName,type,brand,color,notes;
}
