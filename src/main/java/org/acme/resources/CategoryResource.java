package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("15") int size,
            @QueryParam("projected") @DefaultValue("false") boolean projected,
            @QueryParam("term") String term
    ) {

        if (page < 0 || size < 0) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(),
                                   "Page and size must be " +
                                   "greater than or equal to 0.")
                           .build();
        }

        if (projected) {
            return Response.ok(repository.listProjected(term, page, size))
                           .build();
        }

        return Response.ok(repository.list(term, page, size))
                       .build();
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
