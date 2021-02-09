package com.iy.service;

import java.text.DecimalFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iy.dao.AccountRepository;
import com.iy.dto.Account;
import com.iy.exception.AtmException;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	private static DecimalFormat decimalPrecisionFormat = new DecimalFormat("#.##");
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public void depositCash(String accountNumber, double amount) throws AtmException {
		Optional<Account> foundAccount = accountRepository.findById(accountNumber);
		if(foundAccount.isPresent())
		{
			Account account = foundAccount.get();
			account.setBalance(account.getBalance()+Double.parseDouble(decimalPrecisionFormat.format(amount)));
			accountRepository.save(account);
		}
		else
		{
			throw new AtmException("Account Number is not correct");
		}
	}

	@Override
	public void withdrawCash(String accountNumber, double amount)throws AtmException {
		Optional<Account> foundAccount = accountRepository.findById(accountNumber);
		if(foundAccount.isPresent())
		{
			Account account = foundAccount.get();
			account.setBalance(Double.parseDouble(decimalPrecisionFormat.format(account.getBalance()-amount)));
			accountRepository.save(account);		
		
		}
		else
		{
			throw new AtmException("Account Number is not correct");
		}
	}

	@Override
	public double getBalance(String accountNumber) throws AtmException{
		Optional<Account> foundAccount = accountRepository.findById(accountNumber);
		if (foundAccount.isPresent())
		{
			Account account = foundAccount.get();
			return account.getBalance();
		}
		else
		{
			throw new AtmException("Account Number is not correct");
		}
	}

}
