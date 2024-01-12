package org.makerminds.java.service;

import java.util.List;
import java.util.Optional;

import org.makerminds.java.entity.BankAccount;
import org.makerminds.java.entity.Transaction;
import org.makerminds.java.exceptions.BankAccountNotFoundException;
import org.makerminds.java.repository.BankAccountRepository;
import org.makerminds.java.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
	private final TransactionRepository transactionRepository;
	//private final BankAccountService bankAccountService;
	private final BankAccountRepository bankAccountRepository;
	
	
	/*public Transaction createTransaction(Transaction transaction,Long bankAccountId) {
		transaction.setBankAccount(bankAccountService.getBankAccountById(bankAccountId));
		return transactionRepository.save(transaction);
		
		
		public Transaction createTransaction(Transaction transaction,Long bankAccountId) {
		transaction.setBankAccount(bankAccountService.getBankAccountById(bankAccountId));
		return transactionRepository.save(transaction);
	}*/
	/*
	 a. Inject the TransactionRepository and BankAccountRepository dependencies using constructor injection.
b. Check if the id does exist, which means the transaction is being updated.
e. if the transaction is getting updated then use the findById method of BankAccountRepository to retrieve the BankAccount entity corresponding to the given bankAccountId.
d. Set the BankAccount entity as the transaction's bankAccount using transaction.setBankAccount(bankAccount).
e. Save the transaction using transactionRepository.save(transaction) to persist it to the database.
f. Return the saved transaction.

	 */
	public Transaction createTransaction(Long bankAccountId,Transaction transaction) {
		//bankAccountService.findById(bankAccountId);
		BankAccount bankAccount = bankAccountRepository.findById(bankAccountId).orElseThrow(()->
		new BankAccountNotFoundException("Bank Account with id:"+bankAccountId+" has not been found"));
		
		transaction.setBankAccount(bankAccount);
		 return transactionRepository.save(transaction);
		
		
	}
	
	
	
	
	
	/*
	 In the getAllTransactions method:
a. Use the findById method of BankAccountRepository to retrieve the BankAccount entity corresponding to the given bankAccountId.
c. Retrieve and return the list of transactions associated with the BankAccount entity using getTransactionList().

	 */
	public List<Transaction>getAllTransactions (Long bankAccountId){
		/*BankAccount bankAccount = bankAccountRepository.findById(bankAccountId).orElse(null);
		if(bankAccount !=null) {
			return bankAccount.getTransactionList();
		}
		return null;*/
		
	    Optional<BankAccount>optionalBank=bankAccountRepository.findById(bankAccountId);
		BankAccount bankAccount;
		if(optionalBank.isPresent()) {
			bankAccount=optionalBank.get();
			return transactionRepository.findByBankAccount(bankAccount);
		}
		else {
			throw new BankAccountNotFoundException("Bank Account with id:"+bankAccountId+" has not been found");
		}
	}
}
