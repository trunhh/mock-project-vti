package com.trunh.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trunh.dto.JwtResponseDTO;
import com.trunh.form.SignInForm;
import com.trunh.form.AccountCreateForm;
import com.trunh.entity.Account;
import com.trunh.repository.IAccountRepository;
import com.trunh.service.IAccountService;
import com.trunh.utils.JwtUtils;

@RestController
@RequestMapping(value = "api/auth")
@Validated
@CrossOrigin("*")
public class AuthController {
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private IAccountService acService;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private IAccountRepository acRepository;
	@GetMapping("/active_account")
	public void activateAccount(@RequestParam(name = "id") int accountId) {
		acService.activateAccount(accountId);
	}
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody SignInForm signInForm){
		System.out.println("SignInForm: ");
		System.out.println(signInForm);
		Account ac = acRepository.findByUsername(signInForm.getUsername());
		System.out.println(ac);

		if (ac == null) throw new UsernameNotFoundException(signInForm.getUsername());
		if (ac.getStatus().toString().equals("NOT_ACTIVE")) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error: User not actived");
		}
		
		try {
			Authentication auth = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword())
			);
			SecurityContextHolder.getContext().setAuthentication(auth);
			String jwtToken = jwtUtils.generateJwtToken(auth);
			UserDetails userDetails = (UserDetails) auth.getPrincipal();
			return ResponseEntity.ok(new JwtResponseDTO(
				jwtToken, userDetails.getUsername(), userDetails.getAuthorities().toString()
			));
		}catch (Exception ex) {
			System.out.println("Exception authentication: ");
			System.out.println(ex);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password invalid!");
		}
	}
	
	@PostMapping("/signup")
	@Transactional
	public ResponseEntity<?> registerUser(@RequestBody @Valid AccountCreateForm accountCreateForm) {
		System.out.println(accountCreateForm.toString());
		if (acRepository.existsByUsername(accountCreateForm.getUsername())) {
			return ResponseEntity.badRequest().body("Error: Username is already taken!");
		}
		
		if (acRepository.existsByEmail(accountCreateForm.getEmail())) {
			return ResponseEntity.badRequest().body("Error: Email is already taken!");
		}
		
		Account ac = modelMapper.map(accountCreateForm, Account.class);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String enCryptPassword = passwordEncoder.encode(accountCreateForm.getPassword());
		ac.setPassword(enCryptPassword);
		ac.setStatus(Account.AccountStatus.NOT_ACTIVE);
		acService.createAccount(ac);//Send mail active
		return ResponseEntity.ok().body("User registered successfully!");
	}
	
}
