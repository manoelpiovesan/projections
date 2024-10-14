package org.acme.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Table(name = "category")
@SQLDelete(sql = "UPDATE category SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at = '1970-01-01 00:00:00+00'")
public class Category extends AbstractFullEntity {

    @Column(name = "category_name", nullable = false)
    public String name;

    @Column(name = "category_description")
    public String description;

    @Column(name = "category_image")
    public String image;

    @JsonIgnore
    @OneToMany(
            mappedBy = "category",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public List<Product> products;


}
