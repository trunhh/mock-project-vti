package com.trunh.service;

import com.trunh.entity.Order;
import com.trunh.form.OrderFilterForm;
import com.trunh.repository.IOrderRepository;
import com.trunh.specification.OrderSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService{

    @Autowired
    private IOrderRepository oRepository;
    @Override
    public Order getOrderById(int id){
        return oRepository.findById(id).orElse(null);
    };

    @Override
    public Page<Order> getOrderList(Pageable pageable, OrderFilterForm orderFilterForm) {
        Specification<Order> where = OrderSpecification.buildWhere(orderFilterForm);
        return oRepository.findAll(where, pageable);
    };
    @Override
    public void createOrder(Order order) {
        oRepository.save(order);
    };
    @Override
    public void updateOrder(Order order) {
        oRepository.save(order);
    };
    @Override
    public void deleteOrder(int id) {
        oRepository.deleteById(id);
    };
}
