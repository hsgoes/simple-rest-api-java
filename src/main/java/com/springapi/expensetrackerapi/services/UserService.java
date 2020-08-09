package com.springapi.expensetrackerapi.services;

import com.springapi.expensetrackerapi.domain.User;
import com.springapi.expensetrackerapi.exception.EtAuthException;

public interface UserService {

    User validateUser(String email, String password) throws EtAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;
}
