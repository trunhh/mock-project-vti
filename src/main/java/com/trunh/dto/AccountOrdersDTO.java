package com.trunh.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AccountOrdersDTO {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private List<OrderDTO> orders;

    @Getter
    @Setter
    public static class OrderDTO {
        @JsonProperty("orderId")
        private int id;
        private int totalCost;
        private String payment;
        private String address;
        private String status;
        @JsonFormat(pattern = "dd-MM-yyyy")
        private Date createdAt;
    }
}
