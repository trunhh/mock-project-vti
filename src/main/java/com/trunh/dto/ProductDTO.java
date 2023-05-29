package com.trunh.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductDTO {
    private int id;
    private String name;
    private int price;
    private String sku;
    private int quantity;
    private String desc;
    private String gender;
    private String category;
    private String size;
    private String imageUrl;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;
}
