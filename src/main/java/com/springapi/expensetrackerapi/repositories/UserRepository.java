package com.springapi.expensetrackerapi.repositories;

import com.springapi.expensetrackerapi.domain.User;
import com.springapi.expensetrackerapi.exception.EtAuthException;

public interface UserRepository {

    Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;

    User findByEmailAndPassword(String email, String password) throws EtAuthException;

    Integer getCountByEmail(String email);

    User findById(Integer userId);
}
