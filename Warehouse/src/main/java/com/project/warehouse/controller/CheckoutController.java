package com.project.warehouse.controller;

import java.util.HashMap;
import java.util.List;

import com.project.warehouse.entity.CheckoutCart;
import com.project.warehouse.repository.CheckoutCartRepository;
import com.project.warehouse.response.ApiResponse;
import com.project.warehouse.response.MessageResponse;
import com.project.warehouse.security.jwt.ShoppingConfiguration;
import com.project.warehouse.service.CheckoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/checkoutControl")
public class CheckoutController {
	
	@Autowired
	CheckoutService checkoutService;

	@RequestMapping("/orderList")
//    @PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getUserOrders(@RequestBody HashMap<String, String> addCartRequest) {
		try {
			String keys[] = {"userId"};
			if(ShoppingConfiguration.validationWithHashMap(keys, addCartRequest)) {
			}
			
			long userId = Long.parseLong(addCartRequest.get("user_id"));
			
			List<CheckoutCart> obj = checkoutService.getCheckoutByIdUser(userId);
			return ResponseEntity.ok(obj);
				
		}catch (Exception e) {
			e.printStackTrace();
			
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
		}
    }
}
