package org.exercises.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pizze")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotBlank(message = "il nome della pizza non può essere vuoto !!")
    // validation --> oracle bean validation ---> https://docs.oracle.com/javaee/7/tutorial/bean-validation001.htm

    @Column(name = "name", length = 50, nullable = false)
    //@Pattern ---> per evitare caratteri speciali
    private String name;

    @Column(name = "slug", length = 50, nullable = false)
    private String slug;


    //@NotNull(message = "La descrizione è obbligatoria")// validation --> oracle bean validation ---> https://docs.oracle.com/javaee/7/tutorial/bean-validation001.htm
    @Column(name = "description", length = 100, nullable = false)
    @NotBlank
    private String description;
    @NotNull
    @Min(0)
    private BigDecimal price;

    @OneToMany(mappedBy = "pizza", cascade = CascadeType.REMOVE)
    private List<OfferteSpeciali> offerteSpeciali = new ArrayList<>();
@NotBlank
    private String url_photo;
   // getters and setters


    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUrl_photo() {
        return url_photo;
    }

    public void setUrl_photo(String url_photo) {
        this.url_photo = url_photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<OfferteSpeciali> getOfferteSpeciali() {
        return offerteSpeciali;
    }

    public void setOfferteSpeciali(List<OfferteSpeciali> offerteSpeciali) {
        this.offerteSpeciali = offerteSpeciali;
    }
}
