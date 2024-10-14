package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Product;

@ApplicationScoped
public class ProductRepository extends AbstractRepository<Product>{

    @Override
    public PanacheQuery<Product> search(String term) {
        if (term == null) {
            return findAll();
        } else {
            return find("""
                        FROM Product p
                        WHERE p.name
                        LIKE ?1
                        OR p.description LIKE ?1
                        OR p.price LIKE ?1
                        OR p.stock LIKE ?1
                        OR p.image LIKE ?1
                        OR p.category.name LIKE ?1
                        """, "%" + term + "%");

        }
    }



}
