package org.exercises.springlamiapizzeriacrud.repository;

import org.exercises.springlamiapizzeriacrud.model.OfferteSpeciali;
import org.exercises.springlamiapizzeriacrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferteSpecialiRepository extends JpaRepository<OfferteSpeciali,Integer> {
    List<OfferteSpeciali> findByPizza(Pizza pizza);
}
