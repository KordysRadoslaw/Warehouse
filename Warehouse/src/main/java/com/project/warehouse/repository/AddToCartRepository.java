package com.project.warehouse.repository;

import java.util.List;
import java.util.Optional;

import com.project.warehouse.entity.AddToCart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AddToCartRepository extends JpaRepository<AddToCart, Long> {

	@Query("Select sum(addCart.price * addCart.quantity) FROM AddToCart addCart WHERE addCart.user_id=:user_id")
	double getTotalAmountByUserId(@Param("user_id")Long user_id);
	
	
	@Query("Select addCart FROM AddToCart addCart WHERE addCart.user_id=:user_id and addCart.item.id=:item_id")
	Optional<AddToCart> getCartByProductIdAnduserId(@Param("item_id")Long item_id, @Param("user_id")Long user_id);
	
	@Query("Select addCart  FROM AddToCart addCart WHERE addCart.user_id=:user_id")
	List<AddToCart> getCartByUserId(@Param("user_id")Long user_id);
	

	@Modifying
    @Transactional
	@Query("DELETE  FROM AddToCart addCart WHERE addCart.id =:cart_id and addCart.user_id=:user_id")
	void deleteCartByIdAndUserId(@Param("user_id")long user_id,@Param("cart_id")Long cart_id);

	
//	ostatnio dodane, moze wymagaja poprawek
	@Modifying
    @Transactional
	@Query("DELETE FROM AddToCart addCart WHERE addCart.user_id=:user_id")
	void deleteAllCartByUserId(@Param("user_id")Long user_id);
	
	
	@Modifying
    @Transactional
	@Query("update AddToCart addCart set addCart.quantity=:quantity,addCart.price=:price WHERE addCart.id=:cart_id")
	void updateQuantityByCartId(@Param("cart_id")Long cart_id,@Param("price")double price,@Param("quantity")Integer quantity);
	
	
	
}
