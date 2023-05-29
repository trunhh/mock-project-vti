package com.trunh.form;

import com.trunh.entity.Account;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OrderCreateForm {
    @NotBlank(message = "Payment method cannot be blank")
    private String payment;

    private String address;

    private String status;

    private int accountId;
}
