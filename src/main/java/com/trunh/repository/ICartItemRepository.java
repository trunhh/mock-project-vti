package com.trunh.repository;



import com.trunh.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ICartItemRepository extends JpaRepository<CartItem, Integer>, JpaSpecificationExecutor<CartItem>{
    @Modifying
    @Transactional
    @Query("DELETE CartItem cart WHERE cart.id IN(:Ids)")
    void deleteMultiCartItem(List<Integer> Ids);

    @Modifying
    @Transactional
    @Query("DELETE CartItem cart WHERE cart.account = :accountId")
    void deleteAllCartItem(Integer accountId);
}

