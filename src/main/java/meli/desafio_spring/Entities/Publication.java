package meli.desafio_spring.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publication {
    private int userId;
    private int id_post;
    private Date date;
    private PublicationDetail detail;
    private int category;
    private double price;
}
