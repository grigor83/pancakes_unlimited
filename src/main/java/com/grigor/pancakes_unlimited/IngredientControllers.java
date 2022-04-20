package com.grigor.pancakes_unlimited;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pancakes_unlimited.com/API/ingredients")
public class IngredientControllers {
	@Autowired
	IngredientRepo iRepo;
	@Autowired
	PancakeRepo pRepo;
	@Autowired
	//@PersistenceContext
	//EntityManager em;
	
	
	@GetMapping
	public List<Ingredient> listIngredients(){
		return iRepo.findAll();
	}
	
	@PostMapping
	public Ingredient createIngredient(@Valid @RequestBody Ingredient i) {
		return iRepo.save(i);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Ingredient> delete(@PathVariable long id) {	
	    Optional<Ingredient> i = iRepo.findById(id);
	    if (!i.isPresent())
	        return ResponseEntity.notFound().build();
	    
		iRepo.delete(i.get());
        return ResponseEntity.ok().body(i.get());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ingredient> findUserById(@PathVariable long id) {
	    Optional<Ingredient> ingredient = iRepo.findById(id);
	 
	    if(ingredient.isPresent()) {
	        return ResponseEntity.ok().body(ingredient.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Ingredient> updateIngredient(@PathVariable long id, @RequestBody @Valid Ingredient i) {
	    Optional<Ingredient> ingredient = iRepo.findById(id);
	
	    if (ingredient.isPresent()) {
	    	Ingredient ing= ingredient.get();
	    	ing.setName(i.getName());
			ing.setPrice(i.getPrice());
			ing.setCategory(i.getCategory());
			return ResponseEntity.ok().body(iRepo.save(ing));
	    }
	    else
	        return ResponseEntity.notFound().build();
	}
	
}
