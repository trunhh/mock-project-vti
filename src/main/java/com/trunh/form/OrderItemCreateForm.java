package com.trunh.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItemCreateForm {
    private int orderId;
    private int productId;
    private int price;
    private int quantity;
}
