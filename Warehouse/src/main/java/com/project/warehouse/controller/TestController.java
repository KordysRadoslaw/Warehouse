package com.project.warehouse.controller;


import com.project.warehouse.security.services.UserDetailsServiceImpl;
import com.project.warehouse.service.CartService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("/cart")
public class TestController {
	

    @Autowired
    private CartService cartService;

    @Autowired
    private UserDetailsServiceImpl productService;
    
    
    @RequestMapping("/test")
    public String test() {
    	return "test1";
    }
    
    @GetMapping
    public String test2() {
    	return "test2";
    }

    
    
    }
