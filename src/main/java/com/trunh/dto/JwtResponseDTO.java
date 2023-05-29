package com.trunh.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
public class JwtResponseDTO {
	@NonNull
	private String token;
	@NonNull
	private String username;
	@NonNull
	private String role;
}
