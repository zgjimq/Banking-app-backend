package org.makerminds.java.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.makerminds.java.entity.BankAccount;
import org.makerminds.java.service.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/bankAccount")
@CrossOrigin
@RequiredArgsConstructor
public class BankAccountController {
	/*private BankAccountRepository bankAccountRepository;
	public BankAccountController(BankAccountRepository bankAccountRepository) {
		//this.bankAccountRepository = bankAccountRepository;
	}	
	
	@PostMapping(path = "create")
	public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount){
		return ResponseEntity.ok(bankAccountRepository.save(bankAccount));
	}
	
	@GetMapping(path = "all")
	public List<BankAccount>getAllAccounts() {
		return bankAccountRepository.findAll();
	}
	*/
	private final BankAccountService bankAccountService;
	/*public BankAccountController (BankAccountService bankAccountService) {
		this.bankAccountService=bankAccountService;
		@RequiredArgsConstructor
	}*/
	
	/*
	 @PostMapping(path = "/createOrUpdate")
	public BankAccount createOrUpdateBankAccount(@RequestBody BankAccount bankAccount) {
		return bankAccountService.createOrUpdateBankAccount(bankAccount);
	}
	 */
	
	@PostMapping(path = "/createOrUpdate")
	public ResponseEntity<?> createOrUpdateBankAccount(@Valid @RequestBody BankAccount bankAccount,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			Map<String, String> errors= new HashMap<>();
			for(FieldError fieldError : bindingResult.getFieldErrors()) {
				errors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errors,HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(bankAccountService.createOrUpdateBankAccount(bankAccount));
		
	}//good
	@GetMapping(path = "/getAll")
	public List<BankAccount>getAll(){
		return bankAccountService.getBankAccounts();
	}//good
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?>deleteBankAccountById(@PathVariable Long id){
		return bankAccountService.deleteById(id);
	}//good
	@GetMapping(path = "/getBankById/{id}")
	public BankAccount getBankAccountById(@PathVariable Long id) {
		return bankAccountService.findById(id);
	}//good
}
