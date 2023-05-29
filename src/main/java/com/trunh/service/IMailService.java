package com.trunh.service;

public interface IMailService {
	void sendRegistrationUserConfirm(String email);

	void sendUpdatePasswordConfirm(int id, String email);
}
