package org.exercises.springlamiapizzeriacrud.repository;

import org.exercises.springlamiapizzeriacrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Pizza,Integer> {
}
