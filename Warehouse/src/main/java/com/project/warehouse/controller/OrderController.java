package com.project.warehouse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.project.warehouse.entity.AddToCart;
import com.project.warehouse.entity.CheckoutCart;
import com.project.warehouse.response.ApiResponse;
import com.project.warehouse.security.jwt.ShoppingConfiguration;
import com.project.warehouse.service.CartService;
import com.project.warehouse.service.CheckoutService;
import com.project.warehouse.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@CrossOrigin
public class OrderController {
	
	@Autowired
	CheckoutService checkoutService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping("checkoutOrder")
	public ResponseEntity<?> checkout_order(@RequestBody HashMap<String,String> addCartRequest) {
		try {
			String keys[] = {"userId","total_price","pay_type","deliveryAddress"};
			if(ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {
				
				
			}
			long user_Id = Long.parseLong(addCartRequest.get("userId"));
			double total_amt = Double.parseDouble(addCartRequest.get("total_price"));
			if(cartService.checkTotalAmountAgainstCart(total_amt,user_Id)) {
				List<AddToCart> cartItems = cartService.getCartByUserId(user_Id);
				List<CheckoutCart> tmp = new ArrayList<CheckoutCart>();
				for(AddToCart addCart : cartItems) {
					String orderId = ""+getOrderId();
					CheckoutCart cart = new CheckoutCart();
					cart.setPaymentType((addCartRequest.get("pay_type")));
					cart.setPrice(total_amt);
					cart.setUserId(user_Id);
					cart.setOrderId(orderId);
					cart.setItem(addCart.getItem());
					cart.setQuantity(addCart.getQuantity());
					cart.setDeliveryAddress(addCartRequest.get("deliveryAddress"));
					tmp.add(cart);
				}
				cartService.saveProductsForCheckout(tmp);
				return ResponseEntity.ok(new ApiResponse("Order placed successfully", ""));
			}else {
				throw new Exception("Total amount is mismatch");
			}
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
//			return ResponseEntity.badRequest().body(new ApiResponse(addCartRequest, "The order list is empty"));
		}
	}
	public int getOrderId() {
	    Random r = new Random( System.currentTimeMillis() );
	    return 10000 + r.nextInt(20000);
	}
	@RequestMapping("getOrdersByUserId")
		public ResponseEntity<?> getOrdersByUserId(@RequestBody HashMap<String,String> ordersRequest) {
		try {
			String keys[] = {"userId"};	
			return ResponseEntity.ok(new ApiResponse("Order placed successfully", ""));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
		
	}
	
	@RequestMapping("orderList")
	public ResponseEntity<?> getUserOrders(@RequestBody HashMap<String, String> addCartRequest) {
		try {
			String keys[] = {"userId"};
			if(ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {
			}
			
			Long userId = Long.parseLong(addCartRequest.get("userId"));
			
			List<CheckoutCart> obj = checkoutService.getCheckoutByIdUser(userId);
			return ResponseEntity.ok(obj);
			
//			List<CheckoutCart> obj = checkoutService.findByIdUserId(userId);
//			return ResponseEntity.ok(obj);
//			
//
//			List<CheckoutCart> obj = checkoutService.getCheckoutByIdUser(Long.parseLong(addCartRequest.get("userId")));
//			return ResponseEntity.ok(obj);
				
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("TUTAJ!2");
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
  }
}
