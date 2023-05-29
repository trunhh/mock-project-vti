package com.trunh.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.trunh.dto.AccountDTO;
import com.trunh.entity.Account;
import com.trunh.form.AccountFilterForm;

public interface IAccountService extends UserDetailsService{
	
	Page<Account> getAccountList(Pageable pageable, String search, AccountFilterForm acFF);
	
	void updateAccount(AccountDTO acDTO);
	
	void deleteAccount(int id);
	
	void deleteMultipleAccounts(List<Integer> ids);
	
	void addNewAccount(AccountDTO acDTO);
	
	boolean isAccountExistsByUsername(String username);
	
	Account getAccountByUsername(String username);
	
	void createAccount(Account ac);

	Account getAccountById(int accountId);
	
	Account getAccountByEmail(String email);
	
	void activateAccount(int id);

	void changePasswordAccount(Account ac);
	
}
