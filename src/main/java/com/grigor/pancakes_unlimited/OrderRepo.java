package com.grigor.pancakes_unlimited;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

	@Query("SELECT id from Order order")
	List<Long> findIDs();
	
	List<OrderView> findBy();
}
