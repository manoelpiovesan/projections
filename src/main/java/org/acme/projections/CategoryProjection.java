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
    @ProjectedFieldName("COALESCE(SUM(p.stock), 0)")
    public Long absoluteProductCount;
    @ProjectedFieldName("COALESCE(AVG(p.price), 0)")
    public double averagePrice;

    public CategoryProjection(Long id, String name, String description,
                              String image, Long productCount,
                              Long absoluteProductCount, double averagePrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.productCount = productCount;
        this.absoluteProductCount = absoluteProductCount;
        this.averagePrice = averagePrice;

    }

}
