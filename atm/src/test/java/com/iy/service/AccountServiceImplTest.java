package com.iy.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;
import com.iy.dao.AccountRepository;
import com.iy.dto.Account;
import com.iy.exception.AtmException;
import com.iy.service.AccountServiceImpl;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {
	
	@InjectMocks
	private AccountServiceImpl accService;
	
	@Mock
	private AccountRepository accRepository;
	
    @Test
    public void createEmployeeTestSucceeds()
    {
    	Account acc = new Account("120","Raj","Singh","raj12@gmail.com","9009876123","Active",12.37);
         
    	try {
			accService.addAccount(acc);
		} catch (AtmException e) {
			e.printStackTrace();
		}
         
        verify(accRepository, times(1)).save(acc);
    }
    
    @Test(expected=AtmException.class)
    public void createEmployeeTestFails() throws AtmException
    {
    	Account acc = new Account("120","Raj","Singh","raj12@gmail.com","9009876123","Active",12.37);
         
        when(accRepository.findById(acc.getAccNumber()))
        .thenReturn(Optional.of(acc));

		accService.addAccount(acc);
         
        verify(accRepository, times(1)).save(acc);
    }

}
