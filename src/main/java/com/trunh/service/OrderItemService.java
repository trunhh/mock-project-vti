package com.trunh.service;

import com.trunh.entity.OrderItem;
import com.trunh.repository.IOrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService implements IOrderItemService{
    @Autowired
    private IOrderItemRepository orderItemRepository;
    @Override
    public OrderItem getOrderItemById(int id) {
        return orderItemRepository.findById(id).get();
    };
    @Override
    public void createOrderItem(OrderItem orderItem){
        orderItemRepository.save(orderItem);
    };
    @Override
    public void updateOrderItem(OrderItem orderItem){
        orderItemRepository.save(orderItem);
    };
    @Override
    public void deleteOrderItem(int id) {
        orderItemRepository.deleteById(id);
    };
    @Override
    public void deleteMultipleOrderItems(List<Integer> ids){
        orderItemRepository.deleteMultiOrderItem(ids);
    };
    @Override
    public void deleteAllOrderItems(Integer orderId) {orderItemRepository.deleteAllOrderItem(orderId);};
}
