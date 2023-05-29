package com.trunh.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderDetailsDTO {
    private int id;
    private int totalCost;
    private String payment;
    private String address;
    private String status;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;
    private int accountId;
    private List<OrderItemDTO> orderItems;
    @Getter
    @Setter
    public static class OrderItemDTO {
        private String productName;
        private int price;
        private int quantity;
    }
}
