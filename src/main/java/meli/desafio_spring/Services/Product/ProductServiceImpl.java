package meli.desafio_spring.Services.Product;

import meli.desafio_spring.DTO.FollowedPublicationsDTO;
import meli.desafio_spring.DTO.ProductsDTO;
import meli.desafio_spring.DTO.PromosDTO;
import meli.desafio_spring.Entities.*;
import meli.desafio_spring.Repositories.IProductRepository;
import meli.desafio_spring.Repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private IProductRepository listProduct;
    @Autowired
    private IUserRepository listUser;

    @Override
    public PromosDTO getPromosByBuyer(int userId) {
        PromosDTO resp = new PromosDTO();
        int count = 0;
        
        User user = listUser.getUser(userId);
        var db = listProduct.getList();

        if(db.containsKey(userId)){
            var tmpPublications = db.get(userId);
            for (PublicationPromo publication: tmpPublications) {
                if (publication.isHasPromo()){
                  count++;
                }
            }
        } else {
            return resp;
        }

        resp.setUserId(user.getUserId());
        resp.setUserName(user.getUserName());
        resp.setPromoproducts_count(count);

        return resp;
    }

    @Override
    public ProductsDTO getAllProductsByBuyer(int userId, String order) {
        ProductsDTO resp = new ProductsDTO();
        List<PublicationPromo> tmpPromo = new ArrayList<>();

        User user = listUser.getUser(userId);
        var db = listProduct.getList();

        if(db.containsKey(user.getUserId())){
            var tmpPublications = db.get(userId);
            for (PublicationPromo publication: tmpPublications) {
                if (publication.isHasPromo()){
                    tmpPromo.add(publication);
                }
            }
        }
        if (order.equals("date_desc")){
            tmpPromo.sort((d1,d2) -> d2.getDate().compareTo(d1.getDate()));
        }else{
            tmpPromo.sort((d1,d2) -> d1.getDate().compareTo(d2.getDate()));
        }

        resp.setUserId(user.getUserId());
        resp.setUserName(user.getUserName());
        resp.setPost(tmpPromo);

        return resp;
    }

    @Override
    public FollowedPublicationsDTO getFollowedPublications(int userId, String order) {
        FollowedPublicationsDTO resp = new FollowedPublicationsDTO();
        List<Integer> idFollowed = new ArrayList<>();
        List<PublicationBase> tmpPublications = new ArrayList<>();
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_YEAR, -14);
        Date twoWeeksAgo = today.getTime();

        User user = listUser.getUser(userId);

        var tmp = user.getFollowed();
        tmp.sort(Comparator.comparing(UserMinified::getUserName,String.CASE_INSENSITIVE_ORDER));

        tmp.stream().forEach(u -> idFollowed.add(u.getUserId()));


        var db = listProduct.getList();

        for (Map.Entry<Integer, List<PublicationPromo>> post : db.entrySet() ) {
            if(idFollowed.contains(post.getKey())){
                for (PublicationPromo publicationPromo: post.getValue()) {
                    if(publicationPromo.getDate().after(twoWeeksAgo)){
                        tmpPublications.add(new PublicationBase(publicationPromo));
                    }
                }
            }
        }
        if (order.equals("date_desc")){
            tmpPublications.sort((d1,d2) -> d2.getDate().compareTo(d1.getDate()));
        }else{
            tmpPublications.sort((d1,d2) -> d1.getDate().compareTo(d2.getDate()));
        }
        resp.setPosts(tmpPublications);
        resp.setUserId(user.getUserId());

        return resp;
    }

    @Override
    public ResponseEntity<Object> addNewPost(Publication publication) throws IOException {
        return listProduct.addNewPost(publication);
    }

    @Override
    public ResponseEntity<Object> addNewPromoPost(PublicationPromo publication){
        return listProduct.addNewPromoPost(publication);
    }

}
