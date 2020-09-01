package com.telescoped.marketplace.item;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRespository;
    	
	public List<Item> findAll() {
		return this.itemRespository.findAll().stream().filter(item -> item.isAvailable()).collect(Collectors.toList());		
	}
	
	public Optional<Item> findById(Long id) {
		return this.itemRespository.findById(id);
	}
	
	public Item save(Item item) {
		return this.itemRespository.save(item);
    }

    public void deleteById(Long id) {
    	this.itemRespository.deleteById(id);
    }
    
}
