package meli.desafio_spring.Repositories;

import meli.desafio_spring.Entities.Publication;
import meli.desafio_spring.Entities.PublicationPromo;
import org.springframework.http.ResponseEntity;

public interface IProductRepository {

    ResponseEntity<Object> addNewPost(Publication publication);
    ResponseEntity<Object> addNewPromoPost(PublicationPromo publicationPromo);
    Object getList();
}
