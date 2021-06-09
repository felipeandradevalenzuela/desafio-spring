package meli.desafio_spring.Services.Product;

import meli.desafio_spring.DTO.FollowedPublicationsDTO;
import meli.desafio_spring.DTO.ProductsDTO;
import meli.desafio_spring.DTO.PromosDTO;
import meli.desafio_spring.Repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private IProductRepository list;

    @Override
    public PromosDTO getPromosByBuyer(int userId) {
        return null;
    }

    @Override
    public ProductsDTO getAllProductsByBuyer(int userId) {
        return null;
    }

    @Override
    public FollowedPublicationsDTO getFollowedPublications(int userId) {
        return null;
    }
}
