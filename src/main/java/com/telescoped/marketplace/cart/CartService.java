package com.telescoped.marketplace.cart;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telescoped.marketplace.item.Item;
import com.telescoped.marketplace.item.ItemRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	public Optional<Cart> findById(Long id) {
		return this.cartRepository.findById(id);
	}
	
	public Cart createNewCart() {
		Cart cart = new Cart();
		return this.cartRepository.save(cart);
    }
	
	
	public List<Item> getCartItems(Long cartId) {
		
		List<Item> items = null;
		
		Optional<Cart> cart = this.cartRepository.findById(cartId);
		if(cart.isPresent()) {
			items = cart.get().getItems();
		}
		
		return items;
	}
	
	public Optional<Item> addItemToCart(Cart cart, Item item) {
		
		return this.itemRepository.findById(item.getId()).map(itemFound -> {
			itemFound.setAvailable(false);
			itemFound.setCart(cart);
			return this.itemRepository.save(itemFound);
		});
	}
	
	public void deleteItemFromCart(Cart cart, Long itemId) {
		
		this.itemRepository.findById(itemId).map(itemFound -> {
			itemFound.setAvailable(true);
			itemFound.setCart(null);
			return this.itemRepository.save(itemFound);
		});
	}
	
}
