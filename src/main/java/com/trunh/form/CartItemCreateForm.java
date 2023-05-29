package com.trunh.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartItemCreateForm {
    private int accountID;
    private int productID;
    private int quantity;
}
