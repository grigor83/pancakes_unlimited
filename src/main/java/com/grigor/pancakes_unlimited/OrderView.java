package com.grigor.pancakes_unlimited;

import java.util.List;

public interface OrderView {
	long getId();
	String getDescription();
	List<PancakeView> getPancakes();
	double getTotal();
}
