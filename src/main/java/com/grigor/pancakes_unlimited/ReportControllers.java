package com.grigor.pancakes_unlimited;

import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pancakes_unlimited.com/API/reports")
public class ReportControllers {
	@Autowired
	IngredientRepo iRepo;
	@PersistenceContext
	EntityManager em;
	
	@GetMapping
	public Report list(){
		
		LocalDateTime now = LocalDateTime.now();  
		LocalDateTime lastMonth = now.minusDays(30);
		
		Query queryIngredient= em.createNativeQuery("SELECT ingredient_id FROM"
				+ "("
				+ "SELECT ingredient_id, COUNT(*) AS maximum FROM"
				+ "("
				+ "SELECT pancakes_list.order_id, time, pancakes_list.pancake_id, ingredient_id "
				+ "FROM pancakes_list JOIN order_table "
				+ "ON order_table.id = pancakes_list.order_id "
				+ "JOIN ingredients_list "
				+ "ON ingredients_list.pancake_id=pancakes_list.pancake_id "
				+ "WHERE time >= :monthBefore"
				+ ") AS FIRST_TABLE "
				+ "GROUP BY ingredient_id"
				+ ") AS SECOND_TABLE "
				+ "ORDER BY maximum DESC LIMIT 1");
		queryIngredient.setParameter("monthBefore", lastMonth);
		BigInteger id = null;
		try {
			id=  (BigInteger) queryIngredient.getSingleResult();
		}
		catch(NoResultException nor) {
			return new Report (lastMonth, now,null,null);
		}
	    Ingredient ingredient=iRepo.findById(id.longValue()).get();
	    
	    Query queryHealthyIngredient= em.createNativeQuery("SELECT ingredient_id FROM"
				+ "("
				+ "SELECT ingredient_id, COUNT(*) AS maximum FROM"
				+ "("
				+ "SELECT pancakes_list.order_id, time, pancakes_list.pancake_id, ingredient_id, healthy "
				+ "FROM pancakes_list JOIN order_table "
				+ "ON order_table.id = pancakes_list.order_id "
				+ "JOIN ingredients_list "
				+ "ON ingredients_list.pancake_id=pancakes_list.pancake_id "
				+ "JOIN ingredient "
				+ "ON ingredient_id=ingredient.id "
				+ "WHERE healthy AND  time >= :monthBefore "
				+ ") AS FIRST_TABLE "
				+ "GROUP BY ingredient_id"
				+ ") AS SECOND_TABLE "
				+ "ORDER BY maximum DESC LIMIT 1");
	    queryHealthyIngredient.setParameter("monthBefore", lastMonth);
	    try {
			id=  (BigInteger) queryHealthyIngredient.getSingleResult();
		}
		catch(NoResultException nor) {
			return new Report (lastMonth, now, ingredient, null);
		}
	    Ingredient healtyIngredient=iRepo.findById(id.longValue()).get();
	    
		return new Report(lastMonth, now, ingredient, healtyIngredient);
	}
}
