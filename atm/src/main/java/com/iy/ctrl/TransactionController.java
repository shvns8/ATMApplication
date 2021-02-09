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
