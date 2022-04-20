package com.grigor.pancakes_unlimited;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, Long> {
	public Optional<Ingredient> findByName(String name);

}
