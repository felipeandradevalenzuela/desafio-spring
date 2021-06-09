package meli.desafio_spring.Services.Product;

import meli.desafio_spring.DTO.FollowedPublicationsDTO;
import meli.desafio_spring.DTO.ProductsDTO;
import meli.desafio_spring.DTO.PromosDTO;
import org.springframework.stereotype.Service;

@Service
public interface IProductService {

    PromosDTO getPromosByBuyer(int userId);
    ProductsDTO getAllProductsByBuyer(int userId);
    FollowedPublicationsDTO getFollowedPublications(int userId);
}
