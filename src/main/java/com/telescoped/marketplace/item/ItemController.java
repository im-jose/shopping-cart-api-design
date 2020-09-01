package com.telescoped.marketplace.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ItemController {
	
	@Autowired
	private ItemService itemService;


	@GetMapping("/test")
	public ResponseEntity<String> test(){
		return ResponseEntity.ok().body("hello world");
	}
	
	@GetMapping("/items")
	public ResponseEntity<List<Item>> getAllItems() {
		try {
			List<Item> items = this.itemService.findAll();

			if (items.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
	
		    return new ResponseEntity<>(items, HttpStatus.OK);
	
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@GetMapping("/items/{id}")
	public ResponseEntity<Item> getItemById(@PathVariable(value = "id") Long itemId) {
		try {
		
			Optional<Item> item = this.itemService.findById(itemId);
			
			if(item.isPresent()) {
				return new ResponseEntity<>(item.get(), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@PostMapping("/items")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
		try {
			Item newItem = this.itemService.save(item);
			return new ResponseEntity<>(newItem, HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }
	
	@DeleteMapping("/items/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable(value = "id") Long itemId) {
		try {
			this.itemService.deleteById(itemId);
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } 
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }
	
}
