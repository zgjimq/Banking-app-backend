package org.makerminds.java.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankAccountNotFoundExceptionResponse {
	private String bankAccountNotFoundMessage;
	
	public BankAccountNotFoundExceptionResponse(String message) {
		super();
		this.bankAccountNotFoundMessage=message;
	}
}
