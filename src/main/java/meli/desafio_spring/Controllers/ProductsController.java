package meli.desafio_spring.Controllers;

import meli.desafio_spring.DTO.FollowedPublicationsDTO;
import meli.desafio_spring.DTO.ProductsDTO;
import meli.desafio_spring.DTO.PromosDTO;
import meli.desafio_spring.Entities.Publication;
import meli.desafio_spring.Entities.PublicationPromo;
import meli.desafio_spring.Services.Product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ProductsController {

    @Autowired
    private IProductService productService;

    @PostMapping("/products/newpost/")
    public ResponseEntity<Object> addNewPost(@RequestBody Publication publication) throws IOException {
        return productService.addNewPost(publication);
    }

    @GetMapping("/products/followed/{userId}/list/")
    public FollowedPublicationsDTO getFollowedPublications(@PathVariable int userId,@RequestParam(defaultValue = "date_asc") String order){
        return productService.getFollowedPublications(userId,order);
    }

    @PostMapping("/products/newpromopost/")
    public ResponseEntity<Object> addNewPromoPost(@RequestBody PublicationPromo publicationPromo){
        return productService.addNewPromoPost(publicationPromo);
    }

    @GetMapping("/products/{userId}/countPromo/")
    public PromosDTO getPromosByBuyer(@PathVariable int userId){
        return productService.getPromosByBuyer(userId);
    }

    @GetMapping("/products/{userId}/list/")
    public ProductsDTO getAllProductsByBuyer(@PathVariable int userId,@RequestParam(defaultValue = "date_asc") String order){
        return productService.getAllProductsByBuyer(userId,order);
    }

}
