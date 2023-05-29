package com.trunh.repository;



import com.trunh.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface IOrderItemRepository extends JpaRepository<OrderItem, Integer>, JpaSpecificationExecutor<OrderItem>{
    @Modifying
    @Transactional
    @Query("DELETE OrderItem oi WHERE oi.id IN(:Ids)")
    void deleteMultiOrderItem(List<Integer> Ids);

    @Modifying
    @Transactional
    @Query("DELETE OrderItem oi WHERE oi.order = :orderId")
    void deleteAllOrderItem(Integer orderId);
}

