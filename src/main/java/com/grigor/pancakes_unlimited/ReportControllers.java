package com.grigor.pancakes_unlimited;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
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
	@Autowired
	PancakeRepo pRepo;
	//@Autowired
	@Autowired
	OrderRepo oRepo;
	@PersistenceContext
	EntityManager em;
	
	@GetMapping
	public Report list(){
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		LocalDateTime monthBefore = now.minusDays(30);
		
		Query query= em.createNativeQuery("SELECT id from order_table where time >= :monthBefore");
		query.setParameter("monthBefore", monthBefore);
	    List<Object[]> list = query.getResultList();
		
		return new Report(null, null);
	}
}
