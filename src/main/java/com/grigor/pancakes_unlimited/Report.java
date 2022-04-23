package com.grigor.pancakes_unlimited;

public class Report {
	
	private String mostOrderedIngredients;
	private String mostOrderedHealthyIngredients;
	
	public Report(String mostOrderedIngredients, String mostOrderedHealthyIngredients) {
		super();
		this.mostOrderedIngredients = mostOrderedIngredients;
		this.mostOrderedHealthyIngredients = mostOrderedHealthyIngredients;
	}

	public String getMostOrderedIngredients() {
		return mostOrderedIngredients;
	}

	public void setMostOrderedIngredients(String mostOrderedIngredients) {
		this.mostOrderedIngredients = mostOrderedIngredients;
	}

	public String getMostOrderedHealthyIngredients() {
		return mostOrderedHealthyIngredients;
	}

	public void setMostOrderedHealthyIngredients(String mostOrderedHealthyIngredients) {
		this.mostOrderedHealthyIngredients = mostOrderedHealthyIngredients;
	}

}
