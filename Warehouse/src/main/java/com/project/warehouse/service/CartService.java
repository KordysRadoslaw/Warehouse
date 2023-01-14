package com.project.warehouse.service;

import java.util.List;

import com.project.warehouse.entity.AddToCart;
import com.project.warehouse.entity.CheckoutCart;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
	
	List<AddToCart> addCartbyUserIdAndItemId(long itemId, long userId, int quantity, double price) throws Exception;
	 
	List<AddToCart> getCartByUserId(long userId);

	List<AddToCart> removeCartByUserId(long cartId,long userId);

	void updateQuantityByCartId(long cartId, int quantity, double price) throws Exception;
	
	List<AddToCart> removeAllCartByUserId(long userId);
	
	Boolean checkTotalAmountAgainstCart(double totalAmount,long userId);
	
	List<CheckoutCart> getAllCheckoutByUserId(long userId);
	
	List<CheckoutCart> saveProductsForCheckout(List<CheckoutCart> tmp)  throws Exception;
	
	

	
}
