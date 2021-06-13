package meli.desafio_spring.Repositories;

import meli.desafio_spring.Entities.Publication;
import meli.desafio_spring.Entities.PublicationPromo;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface IProductRepository {

    ResponseEntity<Object> addNewPost(Publication publication) throws IOException;
    ResponseEntity<Object> addNewPromoPost(PublicationPromo publicationPromo);
    HashMap<Integer, List<PublicationPromo>> getList();
    Date convertDate(int days);
}
