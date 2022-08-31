package com.martenj.tvservices.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tv_packages")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})

//https://hibernate.atlassian.net/browse/HHH-7668 Hibernate bug, had to implement Serializeable
public class TvPackage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<String> description;

    @Column(name = "price")
    private int price;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tvPackage")
    private List<Order> order;


    public TvPackage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

}
