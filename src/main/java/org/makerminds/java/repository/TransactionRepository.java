package org.makerminds.java.repository;

import java.util.List;
import java.util.Optional;

import org.makerminds.java.entity.BankAccount;
import org.makerminds.java.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long>
{
	public Optional<Transaction> findById(Long id);
	List<Transaction>findByBankAccount(BankAccount bankAccount);
}
