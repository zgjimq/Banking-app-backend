package org.makerminds.java.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
@RestController
public class CustumResponseEntityException extends ResponseEntityExceptionHandler{
	@ExceptionHandler
	public final ResponseEntity<?> handleBankAccountNotFoundException(BankAccountNotFoundException ex){
		BankAccountNotFoundExceptionResponse bankAccountNotFoundExceptionResponse=new BankAccountNotFoundExceptionResponse(ex.getMessage());
		return new ResponseEntity<>(bankAccountNotFoundExceptionResponse,HttpStatus.BAD_REQUEST);		
	}
}
