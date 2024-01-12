package org.makerminds.java.service;

import java.util.List;
import java.util.Optional;

import org.makerminds.java.entity.BankAccount;
import org.makerminds.java.exceptions.BankAccountNotFoundException;
import org.makerminds.java.repository.BankAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
	private final BankAccountRepository bankAccountRepository;
	
	public BankAccountService(BankAccountRepository bankAccountRepository) {
		this.bankAccountRepository=bankAccountRepository;
	}
	
	public BankAccount createOrUpdateBankAccount (BankAccount bankAccount) {
		return bankAccountRepository.save(bankAccount);
	}
	public List<BankAccount>getBankAccounts(){
		return bankAccountRepository.findAll();
	}
	public ResponseEntity<?> deleteById(Long id) {
		/*BankAccount bankAccount= bankAccountRepository.findById(id).orElse(null);
		if(bankAccount !=null) {
			bankAccountRepository.deleteById(id);
			String messageString="U have deleted this id:"+id;
			return ResponseEntity.ok().body(messageString);
		}
		else {
			String messageString="This id does not exist:"+id;
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageString);
		}
		//bankAccountRepository.deleteById(id);*/
		Optional<BankAccount>bankAccount=bankAccountRepository.findById(id);
		//present checks if the bank exist
		if(bankAccount.isPresent()) {
			bankAccountRepository.deleteById(id);
			return ResponseEntity.ok("Bank Account with id:"+id+" has been delete.");
		}
		else {
			throw new BankAccountNotFoundException("Bank Account with the id:" +id + " has not been found");
		}
	}
	
	public BankAccount findById (Long id) {
		BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(()-> new BankAccountNotFoundException("Bank Account with the id:" +id + " has not been found"));
		
		return bankAccount;
		
		
	}
	
}
