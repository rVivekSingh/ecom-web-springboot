package com.example.ecomweb.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecomweb.models.Items;
import com.example.ecomweb.repo.ItemRepository;

@RestController	
public class ItemsRestController {
	
	@Autowired
	ItemRepository itemRepository;
 
	@PostMapping("/item")
	public String saveItem(@RequestBody Items item) {
		System.out.println(item);
		
		Items newItem = itemRepository.save(item);
		
		if(newItem == null) {
		  return "Item IS not SAVED YET";
		}
		return "Item Saved Successfully";
	}
	
	@PutMapping("/item")
	public ResponseEntity updateItem(@RequestBody Items item) {		
		Items updatedItem = itemRepository.save(item);
		
		if(updatedItem == null) {
			new ResponseEntity<>("Updated Not Updated",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Updated Item",HttpStatus.OK);
	}
	
	@GetMapping("/item")
	public ResponseEntity getItem(@RequestParam("id") Long id) {
		if(id != null) {
			Items item = itemRepository.findById(id).get();
			return new ResponseEntity<>(item, HttpStatus.OK);
		}
		return new ResponseEntity<>("Item Fetched",HttpStatus.OK);
	}
	
	@DeleteMapping("/item")
	public ResponseEntity deleteItem(@RequestParam("id") Long id) {
		
		try {
			itemRepository.deleteById(id);
			return new ResponseEntity<>("Item Deleted",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Not Deleted",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/items")
	public ResponseEntity getAllItems() {
		
		List<Items> items = itemRepository.findAll();
		
		return new ResponseEntity<>(items,HttpStatus.OK);
	}
	
}
