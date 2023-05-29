package com.trunh.service;

import com.trunh.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderItemService {
    OrderItem getOrderItemById(int id);
    void createOrderItem(OrderItem orderItem);
    void updateOrderItem(OrderItem orderItem);
    void deleteOrderItem(int id);
    void deleteMultipleOrderItems(List<Integer> ids);
    void deleteAllOrderItems(Integer orderId);
}
