package com.grigor.pancakes_unlimited;

import java.util.List;

public interface PancakeView {
	
	interface IngredientView {
        String getName();
    }
	
	long getId();
	List<IngredientView> getIngredients();    
	double getPrice();
}
