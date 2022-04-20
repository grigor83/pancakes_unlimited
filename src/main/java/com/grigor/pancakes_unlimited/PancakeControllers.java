package com.grigor.pancakes_unlimited;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pancakes_unlimited.com/API/pancakes")
public class PancakeControllers {
	@Autowired 
	PancakeRepo pRepo;
	@Autowired 
	IngredientRepo iRepo;
	
	
	@GetMapping
	public List<Pancake> listPancakes(){
		return pRepo.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Pancake> createPancake(@RequestBody @Valid Pancake p, UriComponentsBuilder ucb) {

		Pancake pancake=pRepo.save(p);
		
	    URI locationUri = ucb.path("/pancakes_unlimited.com/pancakes/").path(String.valueOf(pancake.getId()))
			      .build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(locationUri);
		return  new ResponseEntity<>(pancake, headers, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Pancake> deletePancake(@PathVariable long id) {
		Optional<Pancake> p = pRepo.findById(id);
	    if (!p.isPresent())
	        return ResponseEntity.notFound().build();
		
		Pancake pancake=p.get();
		pRepo.delete(pancake);
        return ResponseEntity.ok().body(pancake);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pancake> findPancakeById(@PathVariable long id) {
	    Optional<Pancake> pancake = pRepo.findById(id);
		 
	    if(pancake.isPresent()) {
	        return ResponseEntity.ok().body(pancake.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pancake> updatePancake(@PathVariable long id,@RequestBody @Valid Pancake p) {	
		Pancake pancake= pRepo.save(p);	
        return ResponseEntity.ok().body(pancake);
	}
}
