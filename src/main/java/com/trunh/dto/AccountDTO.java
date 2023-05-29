package com.trunh.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AccountDTO {
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private String status;
	private String avatarUrl;
	
}
