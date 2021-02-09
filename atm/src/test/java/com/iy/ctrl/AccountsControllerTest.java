package com.iy.ctrl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
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
import com.iy.service.AccountService;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class AccountsControllerTest {
	
	@InjectMocks
	private AccountsController accController;
	
	@Mock
	private AccountService accountService;
	
    @Test
    public void testAddAccountReturnsOk() 
    {
        Account acc = new Account("120","Raj","Singh","raj12@gmail.com","9009876123","Active",12.37);
        
		ResponseEntity<String> result = accController.add(acc);
		
		try{    
			Mockito.verify(accountService, Mockito.times(1)).addAccount(acc);
			
        }catch (AtmException e) {
			e.printStackTrace();
		}
        
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        
        assertThat(result.getBody()).isEqualTo("Account Created successfully");
    }
    
    @Test
    public void testAddAccountReturnsBadRequest() 
    {
        Account acc = new Account("120","Raj","Singh","raj12@gmail.com","9009876123","Active",12.37);
        
        try {
            doThrow(AtmException.class)
            .when(accountService)
            .addAccount(acc);
		} catch (AtmException e1) {
			e1.printStackTrace();
		}
        
        ResponseEntity<String> result = accController.add(acc);
        
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
    }
}
