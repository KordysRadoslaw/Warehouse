package com.project.warehouse.repository;

import java.util.List;

import com.project.warehouse.entity.Item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long> {
	
	public Page<Item> findAll(Pageable pageable);
	
//	public List<Item> findAll();
	
	@Query("select i from Item i where i.name like :regex")
	public List<Item> getItemWithNameLike(@Param("regex") String regex);
	
	@Query("select i from Item i")
	public List<Item> getAllItems();

}
