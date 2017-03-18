package com.springboot.service.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService{

	StrongPasswordEncryptor encryptor;
	
	@Autowired
	public void setEncryptor(StrongPasswordEncryptor encryptor) {
		this.encryptor=encryptor;
	}
	
	@Override
	public String encryptString(String input) {
		return encryptor.encryptPassword(input);
	}

}
