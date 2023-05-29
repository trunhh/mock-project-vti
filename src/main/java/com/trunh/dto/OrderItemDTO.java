package com.trunh.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class OrderItemDTO {
    private int id;
    private int orderId;
    private int productId;
    private int price;
    private int quantity;
}
