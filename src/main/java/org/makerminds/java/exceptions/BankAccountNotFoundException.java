package org.makerminds.java.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BankAccountNotFoundException extends RuntimeException{
	public BankAccountNotFoundException(String message) {
		super(message);
	}
}
