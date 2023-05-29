package com.trunh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class AccountCartDTO {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private List<CartItemDTO> cartItems;
    @Getter
    @Setter
    public static class CartItemDTO {
        @JsonProperty("cartItemId")
        private int id;
        private String productName;
        private int quantity;
    }
}
