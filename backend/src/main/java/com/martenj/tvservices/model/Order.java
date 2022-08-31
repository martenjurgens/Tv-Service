package com.martenj.tvservices.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumns({
            @JoinColumn(name = "tvpackage_id", referencedColumnName = "id"),
            @JoinColumn(name = "tvpackage_name", referencedColumnName = "name")
    })
    private TvPackage tvPackage;

    @Column(name="created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column(name="status")
    private String status = "New";

    @UpdateTimestamp
    @Column(name = "updated_at",
            updatable = false,
            columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updated_at;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public TvPackage getTvPackage() {
        return tvPackage;
    }

    public void setTvPackage(TvPackage tvPackage) {
        this.tvPackage = tvPackage;
    }

    public Order() {
        super();
    }
    public Order(String status) {
        this.status = status;
    }
}
