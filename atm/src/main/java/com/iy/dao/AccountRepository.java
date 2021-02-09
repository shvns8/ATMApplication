package com.iy.dao;

import org.springframework.data.repository.CrudRepository;

import com.iy.dto.Account;

public interface AccountRepository extends CrudRepository<Account, String>
{

}
