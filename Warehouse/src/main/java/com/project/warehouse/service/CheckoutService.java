package com.project.warehouse.service;

import java.util.List;

import com.project.warehouse.entity.CheckoutCart;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public interface CheckoutService {
	
	List<CheckoutCart> getCheckoutByIdUser(long userId);
	
	List<CheckoutCart> findByIdUserId(long user_id);
	

}
