package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ProductRepository extends AbstractRepository<Product> {

    public List<Product> getByCategoryId(Long categoryId, String term, int page,
                                  int size) {
        return search(term, categoryId).page(page, size).list();
    }

    public PanacheQuery<Product> search(String term, Long categoryId) {

        StringBuilder hql = new StringBuilder("FROM Product p");
        Map<String, Object> params = new HashMap<>();

        if (term != null) {
            hql.append(" WHERE p.name like :term");
            params.put("term", "%" + term + "%");
        }

        if (categoryId != null) {
            if (term != null) {
                hql.append(" AND ");
            } else {
                hql.append(" WHERE ");
            }
            hql.append("p.category.id = :categoryId");
            params.put("categoryId", categoryId);
        }

        hql.append(" ORDER BY p.name");

        return find(hql.toString(), params);
    }

}
