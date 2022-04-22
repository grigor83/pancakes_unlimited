package com.grigor.pancakes_unlimited;

import java.util.List;

public interface PancakeView {
	
	interface IngredientView {
        String getName();
    }
	
	long getId();
	List<IngredientView> getIngredientsSet();    
	IngredientView getBasicIngredient();	
}
