package com.iy.service;

import com.iy.exception.AtmException;

public interface TransactionService {
	
	public void depositCash(String accountNumber, double amount)throws AtmException;
	
	public void withdrawCash(String accountNumber, double amount)throws AtmException;
	
	public double getBalance(String accountNumber)throws AtmException;

}
