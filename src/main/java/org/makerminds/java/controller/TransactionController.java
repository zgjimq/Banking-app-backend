package org.makerminds.java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.makerminds.java.entity.Transaction;
import org.makerminds.java.exceptions.BankAccountNotFoundException;
import org.makerminds.java.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
	private final TransactionService transactionService;
	
	@PostMapping(path = "/{bankAccountId}")
	public ResponseEntity<?>createTransaction(@PathVariable Long bankAccountId,@Valid @RequestBody Transaction transaction,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			Map<String, String>errors=new HashMap<>();
			for(FieldError fieldError :  bindingResult.getFieldErrors()) {
				errors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(transactionService.createTransaction(bankAccountId,transaction));
	}	
	@GetMapping(path = "/all/{bankAccountId}")
	public ResponseEntity<?> getAllTransaciton(@PathVariable Long bankAccountId){
		try {
		List<Transaction> transactions=transactionService.getAllTransactions(bankAccountId);
		return new ResponseEntity<>(transactions,HttpStatus.OK);
		}catch (BankAccountNotFoundException ex) {	
			return new ResponseEntity<>("Bank Account with id:"+bankAccountId+" has not been found",HttpStatus.NOT_FOUND);
		}
	}
}
