package com.project.warehouse.controller;

import java.util.List;

import com.project.warehouse.entity.Item;
import com.project.warehouse.response.MessageResponse;
import com.project.warehouse.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/test")
public class BaseController {

	@Autowired
	ItemService itemService;
	
	@GetMapping("/item")
	public List<Item> getAll(){
		
		return itemService.getAllItems();
	} 
	
	
	
}
