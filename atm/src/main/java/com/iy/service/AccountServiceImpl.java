package com.iy.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iy.dao.AccountRepository;
import com.iy.dto.Account;
import com.iy.exception.AtmException;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accRepository;
	
	@Override
	public void addAccount(Account account) throws AtmException{
		
		account.setAccCreationDate(LocalDateTime.now());
		
		Optional<Account> acc = accRepository.findById(account.getAccNumber());
		
		if (acc.isPresent())
		{
			throw new AtmException("Account with Account Number '"+account.getAccNumber()+"' already exists");
		}
		else
		{
			try {
				accRepository.save(account);
			}
			catch (Exception e)
			{
				throw new AtmException("Account could not be created successfully");
			}
		}
	}

	@Override
	public List<Account> getAllAccounts() {
		 ArrayList<Account> accounts = new ArrayList<Account>();
		 
		 accRepository.findAll().forEach(accounts :: add);
		 
		 return accounts;
	}

	@Override
	public Optional<Account> getAccount(String accountNumber) {
		return accRepository.findById(accountNumber);
	}

	@Override
	public void updateAccount(String accountNumber, Account account) {
		Optional<Account> acc= accRepository.findById(accountNumber);
		if(acc.isPresent())
		{
			accRepository.save(account);
		}
	}

	@Override
	public void removeAccount(String accountNumber) {
		accRepository.deleteById(accountNumber);		
	}

}
