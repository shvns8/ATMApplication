package com.iy.ctrl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.iy.dto.Account;
import com.iy.exception.AtmException;
import com.iy.service.AccountService;

@RestController
@RequestMapping(value = "/accounts")
public class AccountsController {
	
	@Autowired
	private AccountService accService;
	
	@PostMapping("/add")
	public ResponseEntity<String> add(@RequestBody Account account){
		try
		{
			accService.addAccount(account);
		}
		catch(AtmException e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.ok("Account Created successfully");
	}
	
	@GetMapping("/getall")
	public List<Account> getAllAccounts() {
		return (List<Account>)accService.getAllAccounts();
	}
	
	@GetMapping("/getbyid/{accountnumber}")
	public Optional<Account> getById(@PathVariable("accountnumber") String accountNumber) {
		return accService.getAccount(accountNumber);
	}

	@PutMapping("/update/{accountnumber}")
	public void update(@PathVariable("accountnumber") String accountNumber, @RequestBody Account account){
		accService.updateAccount(accountNumber, account);
	}
	
	@DeleteMapping("/remove/{accountnumber}")
	public void delete(@PathVariable("accountnumber") String accountNumber){
		accService.removeAccount(accountNumber);
	}

}
