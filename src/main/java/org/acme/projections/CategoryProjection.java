package org.acme.projections;

import io.quarkus.hibernate.orm.panache.common.ProjectedFieldName;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class CategoryProjection {

    @ProjectedFieldName("c.id")
    public Long id;
    @ProjectedFieldName("c.name")
    public String name;
    @ProjectedFieldName("c.description")
    public String description;
    @ProjectedFieldName("c.image")
    public String image;
    @ProjectedFieldName("COALESCE(COUNT(p.id), 0)")
    public Long productCount;

    public CategoryProjection(Long id, String name, String description,
                              String image, Long productCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.productCount = productCount;
    }

}
