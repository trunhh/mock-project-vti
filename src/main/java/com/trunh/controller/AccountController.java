package com.trunh.controller;

import java.util.List;

import com.trunh.dto.AccountCartDTO;
import com.trunh.dto.AccountOrdersDTO;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trunh.dto.AccountDTO;
import com.trunh.entity.Account;
import com.trunh.form.AccountFilterForm;
import com.trunh.service.AccountService;

@RestController
@RequestMapping(value = "api/account")
public class AccountController {
	@Autowired
	private AccountService acService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value="/profile/{username}")
	public AccountDTO getAccountByUsername(@PathVariable(name="username") String username) {
		Account account = acService.getAccountByUsername(username);
		System.out.println(account);
		AccountDTO userInfo = modelMapper.map(account, AccountDTO.class);
		return userInfo;
	}

	@GetMapping(value="/cart/{username}")
	public AccountCartDTO getAccountCartByUsername(@PathVariable(name="username") String username) {
		Account account = acService.getAccountByUsername(username);
		System.out.println(account);
		AccountCartDTO userInfo = modelMapper.map(account, AccountCartDTO.class);
		return userInfo;
	}

	@GetMapping(value="/orders/{username}")
	public AccountOrdersDTO getAccountOrdersByUsername(@PathVariable(name="username") String username) {
		Account account = acService.getAccountByUsername(username);
		System.out.println(account);
		AccountOrdersDTO userInfo = modelMapper.map(account, AccountOrdersDTO.class);
		return userInfo;
	}
	
	@GetMapping(value="/email/{email}")
	public AccountDTO getAccountByEmail(@PathVariable(name="email") String email) {
		Account account = acService.getAccountByEmail(email);
		AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);
		return accountDTO;
	}

	@GetMapping()
	public Page<AccountDTO> getAccountList(Pageable pageable,
		@RequestParam(value = "search", required = false) String search,
		AccountFilterForm acFF
		) {
		System.out.println("Account paging: ");
		System.out.println("acFF: " + acFF.toString());
		Page<Account> pageAccount = acService.getAccountList(pageable, search, acFF);
		List<AccountDTO> list = modelMapper.map(pageAccount.getContent(), new TypeToken< List<AccountDTO> >(){}.getType());
		Page<AccountDTO> page = new PageImpl(list, pageable, pageAccount.getTotalElements());
		return page;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAccount(@PathVariable(name = "id") int id, @RequestBody AccountDTO acDTO) {
		System.out.println("id: " + id);
		System.out.println(acDTO);
		acDTO.setId(id);
		acService.updateAccount(acDTO);
		JSONObject message = new JSONObject();
		message.put("rusultText", "Account updated successfully");
		message.put("status", 200);
		return ResponseEntity.status(HttpStatus.OK).body(message.toString());
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> deleteAccount(@PathVariable(name = "id") int id) {
		acService.deleteAccount(id);
		JSONObject message = new JSONObject();
		message.put("resultText", "Account deleted successfully");
		message.put("status", 200);
		return ResponseEntity.status(HttpStatus.OK).body(message.toString());
	}
	
	@RequestMapping(value = "/deletemultiple", method = RequestMethod.POST)
	public ResponseEntity<?> deleteMultipleAccounts(@RequestBody List<Integer> ids) {
		System.out.println(ids);
		acService.deleteMultipleAccounts(ids);
		JSONObject message = new JSONObject();
		message.put("resultText", "Accounts deleted successfully");
		message.put("status", 200);
		return ResponseEntity.status(HttpStatus.OK).body(message.toString());
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> addNewAccount(@RequestBody AccountDTO acDTO) {
		System.out.println(acDTO);
		acService.addNewAccount(acDTO);
		JSONObject message = new JSONObject();
		message.put("resultText", "Account inserted successfully");
		message.put("status", 200);
		return ResponseEntity.status(HttpStatus.OK).body(message.toString());
	}
	
	@RequestMapping(value = "/password-changing", method = RequestMethod.POST)
	public ResponseEntity<?> changePassword(@RequestParam(value = "username") String username, @RequestParam(value = "newPassword") String newPassword) {
		System.out.println("New password: " + username);
		System.out.println("New password: " + newPassword);
		Account ac = acService.getAccountByUsername(username);
		BCryptPasswordEncoder pEncoder = new BCryptPasswordEncoder();
		String encryptPassword = pEncoder.encode(newPassword);
		ac.setPassword(encryptPassword);
		acService.changePasswordAccount(ac);
		JSONObject message = new JSONObject();
		message.put("resultText", "Account's password changed successfully");
		return ResponseEntity.status(HttpStatus.OK).body(message.toString());
	}
	
}
