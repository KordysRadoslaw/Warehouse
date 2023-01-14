package com.project.warehouse.repository;

import java.util.List;

import com.project.warehouse.entity.CheckoutCart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutCartRepository extends JpaRepository<CheckoutCart, Long>{
	//ta metoda nie dziala, daje blad poniewaz przy get sa brane wszystkie referencje, przy find jesli czegos nie ma to ok
	@Query("Select checkCart  FROM CheckoutCart checkCart WHERE checkCart.user_id=:user_id")
	List<CheckoutCart> getByuserId(@Param("user_id")Long user_id);
	
	@Query("Select checkCart  FROM CheckoutCart checkCart WHERE checkCart.user_id=:user_id")
	List<CheckoutCart> findByIdUserId(@Param("user_id")Long user_id);
}
