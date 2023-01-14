package com.project.warehouse.controller;

import java.util.HashMap;
import java.util.List;

import com.project.warehouse.entity.AddToCart;
import com.project.warehouse.entity.CheckoutCart;
import com.project.warehouse.response.ApiResponse;
import com.project.warehouse.security.jwt.ShoppingConfiguration;
import com.project.warehouse.service.CartService;
import com.project.warehouse.service.CheckoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/addtocart")
@CrossOrigin
public class AddToCartController {
	
	@Autowired
	CartService cartService;
	
	
	@Autowired
	CheckoutService checkoutService;
	
	@RequestMapping("addItem")
	public ResponseEntity<?> addCartWithItem(@RequestBody HashMap<String, String> addCartRequest){
		try {
			String keys[] = {"itemId", "userId", "quantity", "price"};
			
			if(ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {
				
			}
				long itemId = Long.parseLong(addCartRequest.get("itemId"));
				long userId = Long.parseLong(addCartRequest.get("userId"));
				int quantity = Integer.parseInt(addCartRequest.get("quantity"));
				double price = Double.parseDouble(addCartRequest.get("price"));
//				System.out.println(" item id" + itemId + "user id" + userId);
				List<AddToCart> obj = cartService.addCartbyUserIdAndItemId(itemId, userId, quantity, price);
				return ResponseEntity.ok(obj);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
		
	}
	//test
	@RequestMapping("addaxios")
	public ResponseEntity<?> addByAxios(@RequestBody HashMap<String, String> addCartRequest){
		try {
			String keys[] = {"itemId", "userId", "quantity", "price"};
			
			if(ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {
				
			}
				
				long itemId = Long.parseLong(addCartRequest.get("itemId"));
				Long userId = Long.parseLong(addCartRequest.get("userId"));
				int quantity = Integer.parseInt(addCartRequest.get("quantity"));
				double price = Double.parseDouble(addCartRequest.get("price"));
				List<AddToCart> obj = cartService.addCartbyUserIdAndItemId(itemId, userId, quantity, price);
				return ResponseEntity.ok(obj);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
		
	}
	
	
	@RequestMapping("updateQuantityForCart")
  	public ResponseEntity<?> updateQuantityForCart(@RequestBody HashMap<String,String> addCartRequest) {
		try {
			String keys[] = {"cartId","userId","quantity","price"};
			if(ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {
				
			}
			long cartId = Long.parseLong(addCartRequest.get("cartId")); 
			long userId =  Long.parseLong(addCartRequest.get("userId")); 
			int quantity =  Integer.parseInt(addCartRequest.get("quantity")); 
			double price = Double.parseDouble(addCartRequest.get("price"));
			 cartService.updateQuantityByCartId(cartId, quantity, price);
			 List<AddToCart> obj = cartService.getCartByUserId(userId);
			return ResponseEntity.ok(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
		
   }
	
	
	@RequestMapping("removeItemFromCart")
  	public ResponseEntity<?> removeCartwithProductId(@RequestBody HashMap<String,String> removeCartRequest) {
		try {
			String keys[] = {"userId","cartId"};
			if(ShoppingConfiguration.validationWithHashMap(keys, removeCartRequest)) {
				
			}
			List<AddToCart> obj = cartService.removeCartByUserId(Long.parseLong(removeCartRequest.get("cartId")), Long.parseLong(removeCartRequest.get("userId")));
			return ResponseEntity.ok(obj);
		}catch(Exception e) {
				return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}		
   }
	
	
	@RequestMapping("getCartsByUserId")
  	public ResponseEntity<?> getCartsByUserId(@RequestBody HashMap<String,String> getCartRequest) {
		try {
			String keys[] = {"userId"};
			if(ShoppingConfiguration.validationWithHashMap(keys, getCartRequest)) {
			}
			List<AddToCart> obj = cartService.getCartByUserId(Long.parseLong(getCartRequest.get("userId")));
			
			return ResponseEntity.ok(obj);
		}catch(Exception e) {
				
				return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}	
   }
	//moved to OrderController
//	@RequestMapping("orderList")
//	public ResponseEntity<?> getUserOrders(@RequestBody HashMap<String, String> addCartRequest) {
//		try {
//			String keys[] = {"userId"};
//			if(ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {
//			}
//			
//			Long userId = Long.parseLong(addCartRequest.get("userId"));
//			
//			List<CheckoutCart> obj = checkoutService.getCheckoutByIdUser(userId);
//			return ResponseEntity.ok(obj);
//			
////			List<CheckoutCart> obj = checkoutService.findByIdUserId(userId);
////			return ResponseEntity.ok(obj);
////			
////
////			List<CheckoutCart> obj = checkoutService.getCheckoutByIdUser(Long.parseLong(addCartRequest.get("userId")));
////			return ResponseEntity.ok(obj);
//				
//		}catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("TUTAJ!2");
//			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
//		}
//  }
	
}