package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Category;
import org.acme.projections.CategoryProjection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class CategoryRepository extends AbstractRepository<Category> {

    /*
     *  Used to list entities projected.
     *
     * */
    public List<CategoryProjection> listProjected(String term, int page,
                                                  int size) {
        Map<String, Object> params = new HashMap<>();

        /*
         * Here, we have to join the Category and Product tables
         * to calculate the product count, absolute product
         * count, and average price for project. We also have
         * to group by the category fields and order by the
         * category name.
         * */
        StringBuilder hql =
                new StringBuilder(
                        "FROM Category c LEFT JOIN Product p ON " +
                        "c.id = p.category.id");

        if (term != null) {
            hql.append(" WHERE c.name like :term");
            params.put("term", "%" + term + "%");
        }

        hql.append(" GROUP BY c.id, c.name, c.description, c.image");
        hql.append(" ORDER BY c.name");

        return find(hql.toString(), params).page(page, size)
                                           .project(CategoryProjection.class)
                                           .list();
    }

    /*
     * Used to list entities.
     * @param term The term to search for.
     * @param page The page to list.
     * @param size The size of the page.
     */
    public List<Category> list(String term, int page, int size) {
        return search(term).page(page, size).list();
    }

    /*
     * Used to search for entities by a term.
     *  @param term The term to search for.
     */
    public PanacheQuery<Category> search(String term) {
        if (term == null) {
            return findAll();
        }

        return find("name like ?1", "%" + term + "%");
    }

    /*
     * Used to count entities.
     * @param term The term to search for.
     */
    public long count(String term) {
        return search(term).count();
    }

}
