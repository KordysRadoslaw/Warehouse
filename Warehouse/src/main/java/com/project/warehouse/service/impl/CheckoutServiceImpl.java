package com.project.warehouse.service.impl;

import java.util.List;

import com.project.warehouse.entity.CheckoutCart;
import com.project.warehouse.repository.CheckoutCartRepository;
import com.project.warehouse.service.CheckoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	@Autowired
	CheckoutCartRepository checkoutCartRepository;

	@Override
	public List<CheckoutCart> getCheckoutByIdUser(long userId) {

		return checkoutCartRepository.getByuserId(userId);
	}

	@Override
	public List<CheckoutCart> findByIdUserId(long user_id) {
		return checkoutCartRepository.findByIdUserId(user_id);

	}
}