package com.trunh.service;

import com.trunh.entity.Order;
import com.trunh.form.OrderFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    Order getOrderById(int id);
    Page<Order> getOrderList(Pageable pageable, OrderFilterForm orderFilterForm);
    void createOrder(Order order);
    void updateOrder(Order order);
    void deleteOrder(int id);
}
