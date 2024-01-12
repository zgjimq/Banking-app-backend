package org.makerminds.java.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bankAccount")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
	//ketu validim kshtu po funksionon 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bankAccount_accountNumber")
	private Long accountNumber;
	
	@NotBlank(message = "Bank Account balance is required.")
	@Column(name = "bankAccount_balance")
	private String balance;
	@Size(min = 2, max = 20, message = "The size of the Name is invalid")
	@Column(name = "bankAccount_accountOwnersName")
	@NotBlank(message = "Bank Account owner name is required.")
	private String accountOwnersName;
	@Column(name = "bankAccount_accountOwnersPhoneNr")
	@NotBlank(message = "Bank Account phonenumber is required.")
	@Size(min =12, max = 12, message = "Invalid phone number.")
	private String accountOwnersPhoneNr;
	

	@JsonIgnore
	@OneToMany(fetch =FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "bankAccount")
	private List<Transaction> transactionList;
	
}
