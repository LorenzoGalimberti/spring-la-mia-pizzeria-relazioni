package org.exercises.springlamiapizzeriacrud.repository;

import org.exercises.springlamiapizzeriacrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza,Integer> {
    //@Query("SELECT p FROM Pizza p WHERE p.slug = :slug")
    // metodo per trovere by slug
    Optional<Pizza> findBySlug(String slug);

    // Metodo per trovare tutte le pizze ordinate per prezzo ascendente
    List<Pizza> findAllByOrderByPriceAsc();

    // Metodo per trovare tutte le pizze ordinate per prezzo discendente
    List<Pizza> findAllByOrderByPriceDesc();



}
