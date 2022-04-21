package com.grigor.pancakes_unlimited;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping
	public List<Order> orders(){
		return oRepo.findAll();
	}
	
	@PostMapping
	public Order createOrder(@RequestBody @Valid Order o) {
		return oRepo.save(o);
	}

}
