package com.trunh.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.trunh.service.IAccountService;

public class AccountNameNotExistsValidator implements ConstraintValidator<AccountNameNotExists, String>{
	
	@Autowired
	private IAccountService acService;
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return !acService.isAccountExistsByUsername(value);
	}

}
