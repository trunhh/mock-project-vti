package com.trunh.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trunh.entity.Account;
import com.trunh.entity.OrderItem;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private int id;
    private int totalCost;
    private String payment;
    private String status;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;
    private int accountId;
}
