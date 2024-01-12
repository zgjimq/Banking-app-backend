package org.makerminds.java.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Long id;
	@Column(name = "amount")
	
	@Positive(message = "Transaction amount is invalid")
	private int amount;
	@Positive(message = "transactionType is invalid")
	private int transactionType;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bankAccount_accountNumber",updatable = false,nullable = false)
	private BankAccount bankAccount;
	
}
