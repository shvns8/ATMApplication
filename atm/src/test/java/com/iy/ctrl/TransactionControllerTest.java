package com.iy.ctrl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.iy.dto.Account;
import com.iy.exception.AtmException;
import com.iy.service.TransactionServiceImpl;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {
	
	@InjectMocks
	private TransactionController transController;
	
	@Mock
	private TransactionServiceImpl transService;
	
    @Test
    public void testDepositCashReturnsOk() 
    {
        Account acc = new Account("120","Raj","Singh","raj12@gmail.com","9009876123","Active",12.37);
        
        double amount = 12.0;
        
        ResponseEntity<String> result = transController.depositCash(acc.getAccNumber(), amount);
        
        try {
			Mockito.verify(transService, Mockito.times(1)).depositCash(acc.getAccNumber(), amount);
		} catch (AtmException e) {
			e.printStackTrace();
		}
        
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        
        assertThat(result.getBody()).isEqualTo("The amount of "+amount+" is deposited in your account");
    }
    
	
    @Test
    public void testDepositCashReturnsBadRequest() 
    {
    	double amount = 12.0;
        
        String accnumber = "123";
        
        try {
            doThrow(AtmException.class)
            .when(transService)
            .depositCash(accnumber, amount);
		} catch (AtmException e1) {
			e1.printStackTrace();
		}
        
        ResponseEntity<String> result = transController.depositCash(accnumber, amount);
        
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
    }
    
    
    @Test
    public void testWithdrawCashReturnsOk() 
    {
        Account acc = new Account("120","Raj","Singh","raj12@gmail.com","9009876123","Active",12.37);
        
        double amount = 12.0;
        
        ResponseEntity<String> result = transController.withdrawCash(acc.getAccNumber(), amount);
        
        try {
			Mockito.verify(transService, Mockito.times(1)).withdrawCash(acc.getAccNumber(), amount);
		} catch (AtmException e) {
			e.printStackTrace();
		}
        
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        
        assertThat(result.getBody()).isEqualTo("The amount of "+amount+" is withdrawn from your account");
    }
    
    @Test
    public void testWithdrawCashReturnsBadRequest() 
    {   
        double amount = 12.0;
        
        String accnumber = "123";
        
        try {
            doThrow(AtmException.class)
            .when(transService)
            .withdrawCash(accnumber, amount);
		} catch (AtmException e1) {
			e1.printStackTrace();
		}
        
        ResponseEntity<String> result = transController.withdrawCash(accnumber, amount);
        
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
    }
    
    @Test
    public void testGetBalanceReturnsOk() 
    {
        Account acc = new Account("120","Raj","Singh","raj12@gmail.com","9009876123","Active",12.37);
        
        try {
			when(transService.getBalance(acc.getAccNumber())).thenReturn(acc.getBalance());
		} catch (AtmException e1) {
			e1.printStackTrace();
		}
        
        ResponseEntity<String> result = transController.getBalance(acc.getAccNumber());
        
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        
        assertThat(result.getBody()).isEqualTo("Balance = "+acc.getBalance());
    }
    
    @Test
    public void testGetBalanceReturnsBadRequest() 
    {       
        String accnumber = "123";
        
        try {
			when(transService.getBalance(accnumber)).thenThrow(AtmException.class);
		} catch (AtmException e1) {
			e1.printStackTrace();
		}
        
        ResponseEntity<String> result = transController.getBalance(accnumber);
        
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
    }

}
