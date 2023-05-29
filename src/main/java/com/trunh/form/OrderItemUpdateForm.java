package com.trunh.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemUpdateForm {
    private int price;
    private int quantity;
}
