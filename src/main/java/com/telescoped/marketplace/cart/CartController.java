package com.telescoped.marketplace.cart;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telescoped.marketplace.item.Item;
import com.telescoped.marketplace.item.ItemService;


@RestController
@RequestMapping("/api/v1")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/cart/{cartId}/item")
	public ResponseEntity<Cart> getAllItemsFromCart(@PathVariable(value = "cartId") Long cartId) {
		try {
		
			Optional<Cart> cart = this.cartService.findById(cartId);
			
			if(cart.isPresent()) {
				return new ResponseEntity<>(cart.get(), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@PostMapping("/cart")
    public ResponseEntity<Cart> createCart() {
		try {
			
			Cart newCart = this.cartService.createNewCart();
			return new ResponseEntity<>(newCart, HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }
	
	@PostMapping("/cart/{cartId}/item/{itemId}")
    public ResponseEntity<Item> addItemToCart(@PathVariable(value = "cartId") Long cartId, @PathVariable(value = "itemId") Long itemId) {
		try {
			
			Optional<Item> item = this.itemService.findById(itemId);			
			Optional<Cart> cart = this.cartService.findById(cartId);
			
			if(cart.isPresent() && cart.isPresent()) {				
				Optional<Item> newItem = this.cartService.addItemToCart(cart.get(), item.get());
				return new ResponseEntity<>(newItem.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }
	
	@DeleteMapping("cart/{cartId}/item/{itemId}")
    public ResponseEntity<HttpStatus> removeItemFromCart(@PathVariable(value = "cartId") Long cartId, @PathVariable(value = "itemId") Long itemId) {
		try {
			Optional<Cart> cart = this.cartService.findById(cartId);
			
			if(cart.isPresent()) {
				this.cartService.deleteItemFromCart(cart.get(), itemId);
			    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	    } 
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
    }
	
}
