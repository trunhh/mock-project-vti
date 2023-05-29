package com.trunh.form;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ProductCreateForm {
    @NotBlank(message = "Product name cannot be blank")
    private String name;
    @Positive(message = "Price must be greater then zero")
    private int price;
    @NotBlank(message = "SKU number cannot be blank")
    private String sku;
    private int quantity;
    private String desc;
    private String gender;
    private String category;
    private String size;
    private String imageUrl;
}
