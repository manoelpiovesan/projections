package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Category;
import org.acme.projections.CategoryProjection;

import java.util.List;

@ApplicationScoped
public class CategoryRepository extends AbstractRepository<Category> {

    public PanacheQuery<Category> search(String term) {
        if (term == null) {
            return findAll();
        } else {
            return find("""
                        FROM Category c JOIN Product p ON c.id = p.category.id
                        WHERE c.name
                        LIKE ?1
                        OR c.description LIKE ?1
                        GROUP BY c.id, c.name, c.description, c.image
                        """, "%" + term + "%");

        }
    }

    public List<CategoryProjection> listProjection(String term) {
        return search(term).project(CategoryProjection.class).list();
    }

}
