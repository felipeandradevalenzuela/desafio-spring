package meli.desafio_spring.Services.Product;

import meli.desafio_spring.DTO.FollowedPublicationsDTO;
import meli.desafio_spring.DTO.ProductsDTO;
import meli.desafio_spring.DTO.PromosDTO;
import meli.desafio_spring.Entities.Publication;
import meli.desafio_spring.Entities.PublicationPromo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface IProductService {

    PromosDTO getPromosByBuyer(int userId);
    ProductsDTO getAllProductsByBuyer(int userId,String order);
    FollowedPublicationsDTO getFollowedPublications(int userId, String order);
    ResponseEntity<Object> addNewPost(Publication publication) throws IOException;
    ResponseEntity<Object> addNewPromoPost(PublicationPromo publication);
}
