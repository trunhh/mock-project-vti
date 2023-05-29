package com.trunh.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountUpdateForm {
    private String password;
    private String firstName;
    private String lastName;
    private String avatarUrl;
}
