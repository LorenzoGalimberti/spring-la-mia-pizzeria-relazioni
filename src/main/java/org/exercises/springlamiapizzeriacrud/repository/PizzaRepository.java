package org.exercises.springlamiapizzeriacrud.repository;

import org.exercises.springlamiapizzeriacrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza,Integer> {
    @Query("SELECT p FROM Pizza p WHERE p.slug = :slug")
    Optional<Pizza> findPizzaBySlug(@Param("slug") String slug);

}
