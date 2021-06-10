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

    public PublicationPromo(Publication publication) {
        setUserId(publication.getUserId());
        setId_post(0);
        setDate(publication.getDate());
        setDetail(publication.getDetail());
        setCategory(publication.getCategory());
        setPrice(publication.getPrice());
        setHasPromo(false);
        setDiscount(0.0);
    }
}
