package org.exercises.springlamiapizzeriacrud.repository;

import org.exercises.springlamiapizzeriacrud.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredienteRepository extends JpaRepository<Ingrediente,Integer> {

}
