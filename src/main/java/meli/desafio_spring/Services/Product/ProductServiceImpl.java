package meli.desafio_spring.Services.Product;

import meli.desafio_spring.DTO.FollowedPublicationsDTO;
import meli.desafio_spring.DTO.ProductsDTO;
import meli.desafio_spring.DTO.PromosDTO;
import meli.desafio_spring.Entities.*;
import meli.desafio_spring.Repositories.IProductRepository;
import meli.desafio_spring.Repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * Metodo para obtener todas las promociones de un vendedor especifico
     * @param userId
     * @return
     */
    @Override
    public PromosDTO getPromosByBuyer(int userId) {
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
            throw new NullPointerException("No existen promociones de este usuario");
        }
        return new PromosDTO(user,count);
    }

    /**
     *Metodo para obtener todos los productos de un vendedor
     * @param userId
     * @param order
     * @return
     */
    @Override
    public ProductsDTO getAllProductsByBuyer(int userId, String order) {
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
        sortDate(tmpPromo,order);
        return new ProductsDTO(user,tmpPromo);
    }

    /**
     * Metodo para obtener todos los productos a partir de los vendedores
     * seguidos por un usuario
     * @param userId
     * @param order
     * @return
     */
    @Override
    public FollowedPublicationsDTO getFollowedPublications(int userId, String order) {
        FollowedPublicationsDTO resp = new FollowedPublicationsDTO();
        List<Integer> idFollowed = new ArrayList<>();
        List<PublicationBase> tmpPublications = new ArrayList<>();
        Date twoWeeksAgo = listProduct.convertDate(-14);
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
        sortDate(order, tmpPublications);
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


    /**
     * Metodos de ordenamiento de fecha sobrecargados
     * @param list
     * @param order
     */
    public void sortDate(List<PublicationPromo> list, String order){
        if (order.equals("date_desc")){
            list.sort((d1,d2) -> d2.getDate().compareTo(d1.getDate()));
        }else{
            list.sort((d1,d2) -> d1.getDate().compareTo(d2.getDate()));
        }
    }
    public void sortDate( String order, List<PublicationBase> list){
        if (order.equals("date_desc")){
            list.sort((d1,d2) -> d2.getDate().compareTo(d1.getDate()));
        }else{
            list.sort((d1,d2) -> d1.getDate().compareTo(d2.getDate()));
        }
    }

}
