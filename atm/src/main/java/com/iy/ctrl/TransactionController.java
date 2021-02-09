/**
 * The class provides API's to the user for performing depositing cash, withdrawing cash, checking balance for an account
 */
package com.iy.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iy.exception.AtmException;
import com.iy.service.TransactionService;

@RestController
@RequestMapping(value="/transact")
public class TransactionController {
	
	@Autowired
	private TransactionService tranService;
	
	/**
	 * The method is used to deposit cash into an account
	 * 
	 * @param accNumber - holds the account number into which cash is to be deposited
	 * 
	 * @param amount - holds the amount of cash which needs to be deposited
	 * 
	 * @return 200 OK - when cash deposited
	 * 
	 * @return 400 Bad request - when cash could not be deposited
	 */
	@PutMapping(value="/deposit")
	public ResponseEntity<String> depositCash(@RequestParam(name="accnumber") String accNumber, @RequestParam(name="amount") double amount)
	{
		try
		{
			tranService.depositCash(accNumber, amount);		
		}
		catch(AtmException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.ok("The amount of "+amount+" is deposited in your account");
	}
	
	/**
	 * The method is used to withdraw cash into an account
	 * 
	 * @param accNumber - holds the account number from which cash is to be withdrawn
	 * 
	 * @param amount - holds the amount of cash which needs to be withdrawn
	 * 
	 * @return 200 OK - when cash withdrawn
	 * 
	 * @return 400 Bad request - when cash could not be withdrawn
	 */
	@PutMapping(value="/withdraw")
	public ResponseEntity<String> withdrawCash(@RequestParam(name="accnumber") String accNumber, @RequestParam(name="amount") double amount)
	{
		try {
			tranService.withdrawCash(accNumber, amount);
		}
		catch(AtmException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.ok("The amount of "+amount+" is withdrawn from your account");
	}
	
	/**
	 * The method is used to check balance in an account
	 * 
	 * @param accNumber - holds the account number for which the balance is to be checked
	 * 
	 * @return 200 OK - when balance retrieved 
	 * 
	 * @return 400 Bad request - when balance could not be retrieved
	 */
	@GetMapping(value="/getbalance/{accnumber}")
	public ResponseEntity<String> getBalance(@PathVariable("accnumber") String accNumber)
	{
		try {
			return ResponseEntity.ok("Balance = "+tranService.getBalance(accNumber));
		}
		catch(AtmException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
