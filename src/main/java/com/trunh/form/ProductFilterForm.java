package com.trunh.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFilterForm {

    private String name;

    private String gender;

    private String category;

    private String size;

    private int minPrice;

    private int maxPrice;
}
