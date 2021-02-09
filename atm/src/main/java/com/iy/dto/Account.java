package com.iy.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="accounts")
public class Account {
	
	@Id
	private String accNumber;
	private String accholderFirstName;
	private String accholderLastName;
	private String email;
	private String contactNumber;
	private String accStatus;
	private LocalDateTime accCreationDate;
	private double balance;
	
	public Account() {
		super();
	}

	public Account(String accNumber, String accholderFirstName, String accholderLastName, String email,
			String contactNumber, String accStatus, double balance) {
		super();
		this.accNumber = accNumber;
		this.accholderFirstName = accholderFirstName;
		this.accholderLastName = accholderLastName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.accStatus = accStatus;
		this.balance = balance;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public String getAccholderFirstName() {
		return accholderFirstName;
	}

	public void setAccholderFirstName(String accholderFirstName) {
		this.accholderFirstName = accholderFirstName;
	}

	public String getAccholderLastName() {
		return accholderLastName;
	}

	public void setAccholderLastName(String accholderLastName) {
		this.accholderLastName = accholderLastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}

	public LocalDateTime getAccCreationDate() {
		return accCreationDate;
	}

	public void setAccCreationDate(LocalDateTime accCreationDate) {
		this.accCreationDate = accCreationDate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	

}
