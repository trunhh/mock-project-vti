package com.trunh.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {
    private int id;
    private int accountID;
    private int productID;
    private int quantity;
}
