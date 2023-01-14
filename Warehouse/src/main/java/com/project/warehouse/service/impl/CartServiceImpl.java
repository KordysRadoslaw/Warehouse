	package com.project.warehouse.service.impl;

import java.util.List;

import com.project.warehouse.entity.AddToCart;
import com.project.warehouse.entity.CheckoutCart;
import com.project.warehouse.entity.Item;
import com.project.warehouse.repository.AddToCartRepository;
import com.project.warehouse.repository.CheckoutCartRepository;
import com.project.warehouse.repository.ItemRepository;
import com.project.warehouse.service.CartService;
import com.project.warehouse.service.ItemService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	AddToCartRepository addToCartRepository;
	
	@Autowired
	CheckoutCartRepository checkoutCartRepository;
	
	@Autowired
	ItemService itemService;
	
	private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
	
	
	@Override
	public List<AddToCart> addCartbyUserIdAndItemId(long itemId, long userId, int quantity, double price) throws Exception {
		
			try {
				if(addToCartRepository.getCartByProductIdAnduserId(userId, itemId).isPresent()) {
					throw new Exception("Product is already exist.");
				}
				
				AddToCart obj = new AddToCart();
				obj.setQuantity(quantity);
				obj.setUser_id(userId);
				Item item = itemService.getItemById(itemId);
				
				obj.setItem(item); 
				//TODO price has to check with qty
				obj.setPrice(price);
				addToCartRepository.save(obj);	
				return this.getCartByUserId(userId);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("" + e .getMessage());
				throw new Exception(e.getMessage());
			}
	}


	@Override
	public List<AddToCart> getCartByUserId(long userId) {
		return addToCartRepository.getCartByUserId(userId);
	}


	@Override
	public List<AddToCart> removeCartByUserId(long cartId, long userId) {
		addToCartRepository.deleteCartByIdAndUserId(userId, cartId);
		return this.getCartByUserId(userId);
		
	}


	@Override
	public void updateQuantityByCartId(long cartId, int quantity, double price) throws Exception {
		addToCartRepository.updateQuantityByCartId(cartId,price,quantity);
		
	}


	@Override
	public List<AddToCart> removeAllCartByUserId(long userId) {
		addToCartRepository.deleteAllCartByUserId(userId);
		return null;
	}


	@Override
	public Boolean checkTotalAmountAgainstCart(double totalAmount, long userId) {
		double total_amount =addToCartRepository.getTotalAmountByUserId(userId);
		if(total_amount == totalAmount) {
			return true;
		}
		System.out.print("Error from request "+total_amount +" --db-- "+ totalAmount);
		return false;
	}


	@Override
	public List<CheckoutCart> getAllCheckoutByUserId(long userId) {
		return checkoutCartRepository.getByuserId(userId);
	}


	@Override
	public List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp) throws Exception {
		try {
			long user_id = tmp.get(0).getUserId();
			if(tmp.size() >0) {
				checkoutCartRepository.saveAll(tmp);
				this.removeAllCartByUserId(user_id);
				return this.getAllCheckoutByUserId(user_id);
			}	
			else {
				throw  new Exception("Should not be empty");
			}
		}catch(Exception e) {
			throw new Exception("Error while checkout "+e.getMessage());
		}
	}



}
