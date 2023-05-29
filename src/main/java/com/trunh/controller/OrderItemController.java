package com.trunh.controller;

import com.trunh.dto.OrderItemDTO;
import com.trunh.entity.OrderItem;
import com.trunh.form.OrderItemCreateForm;
import com.trunh.form.OrderItemUpdateForm;
import com.trunh.service.OrderItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping(value = "api/order-item")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public OrderItemDTO getOrderItemById(Pageable pageable, @RequestParam("id") int id) {
        OrderItem orderItem = orderItemService.getOrderItemById(id);
        OrderItemDTO orderItemDTO = modelMapper.map(orderItem, OrderItemDTO.class);
        return orderItemDTO;

    };
    @PostMapping
    public ResponseEntity<?> createOrderItem(@RequestBody @Valid OrderItemCreateForm form) {
        System.out.println("Create order: ");
        System.out.println(form.toString());
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        OrderItem orderItem = modelMapper.map(form, OrderItem.class);
        orderItemService.createOrderItem(orderItem);
        return ResponseEntity.ok().body("Add item to order successfully");

    }
    @PutMapping
    public ResponseEntity<?> updateOrderItem(@RequestParam(name = "id") int id, @RequestBody @Valid OrderItemUpdateForm form) {
        OrderItem orderItem = modelMapper.map(form, OrderItem.class);
        orderItem.setId(id);
        orderItemService.updateOrderItem(orderItem);
        return ResponseEntity.ok().body("Update order item succcessfully");
    }
    @DeleteMapping
    public ResponseEntity<?> deleteOrderItem(@RequestParam(name="id") int id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.ok().body("Deleted product successfully");
    }
    @DeleteMapping("/multiple")
    public ResponseEntity<?> deleteMultipleOrderItem(@RequestBody List<Integer> ids) {
        orderItemService.deleteMultipleOrderItems(ids);
        return ResponseEntity.ok().body("Deleted products successfully");
    }
    @DeleteMapping("/clear")
    public ResponseEntity<?> deleteAllOrderItem(@RequestParam(name="accountId") int accountId) {
        orderItemService.deleteAllOrderItems(accountId);
        return ResponseEntity.ok().body("Deleted products successfully");
    }
}
