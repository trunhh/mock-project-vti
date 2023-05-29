package com.trunh.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.trunh.dto.AccountDTO;
import com.trunh.entity.Account;
import com.trunh.form.AccountFilterForm;
import com.trunh.event.OnSendRegistrationUserConfirmViaEmailEvent;
import com.trunh.event.OnUpdatePasswordEvent;
import com.trunh.repository.IAccountRepository;
import com.trunh.specification.AccountSpecification;

@Service
public class AccountService implements IAccountService{
	@Autowired
	private IAccountRepository acRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Override
	public Page<Account> getAccountList(Pageable pageable, String search, AccountFilterForm acFF) {
		Specification<Account> where = AccountSpecification.buildWhere(search, acFF);
		return acRepository.findAll(where, pageable);
	}

	@Override
	public void updateAccount(AccountDTO acDTO) {
		Account ac = modelMapper.map(acDTO, Account.class);
		acRepository.save(ac);
	}


	@Override
	public void addNewAccount(AccountDTO acDTO) {
		Account ac = modelMapper.map(acDTO, Account.class);
		Account account = acRepository.save(ac);
		System.out.println("account save: ");
		System.out.println(account);
	}

	@Override
	public void deleteAccount(int id) {
		acRepository.deleteById(id);
	}

	@Override
	public void deleteMultipleAccounts(List<Integer> ids) {
		acRepository.deleteMultilAccount(ids);
	}

	@Override
	public boolean isAccountExistsByUsername(String username) {
		return acRepository.existsByUsername(username);
	}

	@Override
	public Account getAccountByUsername(String username) {
		return acRepository.findByUsername(username);
	}

	@Override
	public void createAccount(Account ac) {
		acRepository.save(ac);
		
		//Send mail active
		sendConfirmUserRegistrationViaEmail(ac.getEmail());
	}
	
	private void sendConfirmUserRegistrationViaEmail(String email) {
		eventPublisher.publishEvent(new OnSendRegistrationUserConfirmViaEmailEvent(email));
	}
	
	@Override
	public void changePasswordAccount(Account ac) {
		System.out.println(ac.getId());
		System.out.println(ac.getPassword());
		
		acRepository.changePasswordAccount(ac.getId(), ac.getPassword());
		//Send mail active
		sendConfirmUpdatePasswordViaEmail(ac);
	}
	
	private void sendConfirmUpdatePasswordViaEmail(Account ac) {
		eventPublisher.publishEvent(new OnUpdatePasswordEvent(ac));
	}
	
	@Override
	public Account getAccountById(int accountId) {
		return acRepository.findById(accountId).get();
	}

	@Override
	public Account getAccountByEmail(String email) {
		// TODO Auto-generated method stub
		return acRepository.getAccountByEmail(email);
	}

	@Override
	public void activateAccount(int id) {
		Account ac = acRepository.getById(id);
		ac.setStatus(Account.AccountStatus.ACTIVE);
		acRepository.save(ac);
	}

//	public Account getAccountByUsername(String username) {
//		// TODO Auto-generated method stub
//		return acRepository.findByUsername(username);
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account ac = acRepository.findByUsername(username);
		
		System.out.println("load user: ");
		System.out.println(ac);
		
		if (ac == null) throw new UsernameNotFoundException(username);
		if (ac.getRole() != null) {
			return new User(
					ac.getUsername(),
					ac.getPassword(),
					AuthorityUtils.createAuthorityList(ac.getRole().toString())
			);
		}else {
			return new User(
					ac.getUsername(),
					ac.getPassword(),
					AuthorityUtils.createAuthorityList("EMPLOYEE")
			);
		}
	}
	
}
