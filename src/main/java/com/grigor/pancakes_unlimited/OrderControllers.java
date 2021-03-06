package com.grigor.pancakes_unlimited;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pancakes_unlimited.com/API/orders")

public class OrderControllers {
	@Autowired
	OrderRepo oRepo;
	@Autowired 
	PancakeRepo pRepo;
	@PersistenceContext
	EntityManager em;
	
	@GetMapping
	public List<OrderView> orders(){
		return oRepo.findBy();
	}
	
	@PostMapping
	public ResponseEntity<String> createOrder(@RequestBody @Valid Order o) {		
		for (Pancake pancake : o.getPancakes()) {
			Query query= em.createNativeQuery("SELECT pancake_id from pancakes_list where pancake_id="+pancake.getId());
		    @SuppressWarnings("unchecked")
			List<Object[]> list = query.getResultList();
			if (list.size()!=0)
		        return new ResponseEntity<>("Your order is not accepted! One of pancakes in order list has already been purchased!", HttpStatus.BAD_REQUEST);
		}
				
		LocalDateTime now = LocalDateTime.now();
		o.setTime(now);
		oRepo.save(o);
        return new ResponseEntity<>("Your order is accepted!", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderView> findUserById(@PathVariable long id) {
	    Optional<Order> order = oRepo.findById(id);  
	    
	    if(order.isPresent()) {
	        return ResponseEntity.ok().body(oRepo.findOrderById(id));
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

}
