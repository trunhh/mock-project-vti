package com.trunh.controller;

import com.trunh.dto.OrderDTO;
import com.trunh.dto.OrderDetailsDTO;
import com.trunh.entity.Order;
import com.trunh.form.OrderCreateForm;
import com.trunh.form.OrderFilterForm;
import com.trunh.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelMapper modelMapper;



    @GetMapping("/{id}")
    public OrderDetailsDTO getOrderById(@PathVariable(name="id") int id) {
        Order order = orderService.getOrderById(id);
        OrderDetailsDTO orderDTO = modelMapper.map(order, OrderDetailsDTO.class);
        return orderDTO;
    }


    @GetMapping()
    public Page<OrderDTO> getOrderList(Pageable pageable, OrderFilterForm pFF) {
        System.out.println("Order paging: ");
        System.out.println("pFF: " + pFF.toString());
        Page<Order> orderPage = orderService.getOrderList(pageable, pFF);
        List<OrderDTO> orderDTOList = modelMapper.map(orderPage.getContent(), new TypeToken<List<OrderDTO>>(){}.getType());
        Page<OrderDTO> orderDTOPage = new PageImpl(orderDTOList, pageable, orderPage.getTotalElements());
        return orderDTOPage;
    }


    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderCreateForm cpf) {
        System.out.println("Create order: ");
        System.out.println(cpf.toString());

        Order order = modelMapper.map(cpf, Order.class);
        orderService.createOrder(order);
        return  ResponseEntity.ok().body("Created order successfully!");
    }

    @PutMapping
    public ResponseEntity<?> updateOrder(@RequestParam(name = "id") int id, @RequestBody @Valid OrderCreateForm cpf) {
        System.out.println("Update order: ");
        System.out.println(cpf.toString());

        Order order = modelMapper.map(cpf, Order.class);
        order.setId(id);

        System.out.println("Update order: ");
        System.out.println(order.toString());

        orderService.updateOrder(order);
        return ResponseEntity.ok().body("Updated order successfully!");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteOrder(@RequestParam(name = "id") int id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().body("Deleted order successfully!");
    }
}
