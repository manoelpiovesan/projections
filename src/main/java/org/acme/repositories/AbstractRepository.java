package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.List;

abstract class AbstractRepository<T>
        implements PanacheRepositoryBase<T, Long> {

    /*
     * Used to search for entities by a term.
     *  @param term The term to search for.
     */
    public abstract PanacheQuery<T> search(String term);

    /*
     * Used to list entities.
     * @param first The first entity to list.
     * @param pageSize The number of entities to list.
     * @param id The id of the entity to list.
     * @param term The term to search for.
     */
    public List<T> list(int first, int pageSize, Long id, String term) {
        if (id != null) {
            return find("id", id).list();
        }

        return search(term).page(first / pageSize, pageSize).list();
    }

    /*
     * Used to count entities.
     * @param term The term to search for.
     */
    public long count(String term) {
        return search(term).count();
    }

}
