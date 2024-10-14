package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Category;
import org.acme.projections.CategoryProjection;
import org.acme.repositories.CategoryRepository;

import java.util.Date;
import java.util.List;

@Path("/categories")
public class CategoryResource {

    @Inject
    CategoryRepository repository;

    @GET
    @Path("/projected")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryProjection> listProjected(
            @QueryParam("term") String term) {
        return repository.listProjected(term);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> list(
            @QueryParam("first") int first,
            @QueryParam("pageSize") int pageSize,
            @QueryParam("id") Long id,
            @QueryParam("term") String term) {
        return repository.list(first, pageSize, id, term);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/count")
    public long count(@QueryParam("term") String term) {
        return repository.count(term);
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Category add(Category category) {
        category.createdAt = new Date();
        category.updatedAt = new Date();

        repository.persist(category);
        return category;
    }

}
