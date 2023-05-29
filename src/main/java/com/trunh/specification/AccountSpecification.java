package com.trunh.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.trunh.entity.Account;
import com.trunh.form.AccountFilterForm;

public class AccountSpecification {
	
	public static Specification<Account> buildWhere(String search, AccountFilterForm acFF){
		Specification<Account> where = null;
		
		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
			CustomAccountSpecification userName = new CustomAccountSpecification("userName", search);
			CustomAccountSpecification firstName = new CustomAccountSpecification("firstName", search);
			CustomAccountSpecification lastName = new CustomAccountSpecification("lastName", search);
			
			where = Specification.where(userName).or(firstName).or(lastName);
		}
		
		if (acFF != null && !StringUtils.isEmpty(acFF.getRole())) {
			CustomAccountSpecification accountRole = new CustomAccountSpecification("role", acFF.getRole());
			if (where == null) where = accountRole;
			else where = where.and(accountRole);
		}
		System.out.println(where);
		return where;
	}
}
