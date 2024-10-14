package org.acme.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at = '1970-01-01 00:00:00+00'")
public class Product extends AbstractFullEntity {

    @Column(name = "product_name", nullable = false)
    public String name;

    @Column(name = "product_price", nullable = false)
    public Double price;

    @Column(name = "product_stock", nullable = false)
    public Integer stock;

    @Column(name = "product_description")
    public String description;

    @Column(name = "product_image")
    public String image;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    public Category category;

}
