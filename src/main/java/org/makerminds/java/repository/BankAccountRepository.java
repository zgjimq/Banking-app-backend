package org.makerminds.java.repository;

import java.util.Optional;

import org.makerminds.java.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{
	public Optional<BankAccount> findById(Long id);
}
