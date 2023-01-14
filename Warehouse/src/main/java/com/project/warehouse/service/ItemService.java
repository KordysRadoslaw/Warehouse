package com.project.warehouse.service;

import java.util.List;

import com.project.warehouse.entity.Item;
import com.project.warehouse.repository.ItemRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemService {
	
	private final ItemRepository itemRepository;

	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	public List<Item> getAllItems() {
		return itemRepository.getAllItems();
	}
	
	public Item getItemById(Long itemId) throws Exception {
		return itemRepository.findById(itemId).orElseThrow(() -> new Exception("Product is not found"));
	}
	
	
	
	
	

}
