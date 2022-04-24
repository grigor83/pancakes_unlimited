package com.grigor.pancakes_unlimited;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PancakeRepo  extends JpaRepository<Pancake, Long>{
	public List<PancakeView> findBy();
	public List<Pancake> findByBasicIngredient(Ingredient i);
}
