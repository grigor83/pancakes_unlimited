package com.grigor.pancakes_unlimited;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Report {
	
	DateTimeFormatter dtf;
	private String title;
	private Ingredient mostOrderedIngredient, mostOrderedHealthyIngredient;
	
	public Report(LocalDateTime lastMonth, LocalDateTime now, Ingredient mostOrderedIngredient, Ingredient mostOrderedHealthyIngredient) {
		super();
		dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		title="REPORTING PERIOD: from "+lastMonth.format(dtf) +" to "+now.format(dtf);
		this.mostOrderedIngredient = mostOrderedIngredient;
		this.mostOrderedHealthyIngredient = mostOrderedHealthyIngredient;
	}

	public String getTitle() {
		return title;
	}

	public Ingredient getMostOrderedIngredient() {
		return mostOrderedIngredient;
	}

	public Ingredient getMostOrderedHealthyIngredient() {
		return mostOrderedHealthyIngredient;
	}

}
