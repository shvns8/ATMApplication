package com.iy.service;

import java.util.List;
import java.util.Optional;
import com.iy.dto.Account;
import com.iy.exception.AtmException;

public interface AccountService {
	
	public List<Account> getAllAccounts();
	
	public Optional<Account> getAccount(String accountNumber);
	
	public void addAccount(Account account) throws AtmException;
	
	public void updateAccount(String accountNumber, Account account);
	
	public void removeAccount(String accountNumber);
}
