package meli.desafio_spring.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id_post", "date", "detail", "category", "price"})
public class PublicationBase {
    private int id_post;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private PublicationDetail detail;
    private int category;
    private double price;

    public PublicationBase(PublicationPromo publication) {
        setId_post(publication.getId_post());
        setDate(publication.getDate());
        setDetail(publication.getDetail());
        setCategory(publication.getCategory());
        setPrice(publication.getPrice());
    }
}
