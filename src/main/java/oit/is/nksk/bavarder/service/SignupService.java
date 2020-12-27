package oit.is.nksk.bavarder.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import oit.is.nksk.bavarder.model.Account;
import oit.is.nksk.bavarder.model.AccountMapper;

@Service
@Transactional
public class SignupService {

  @Autowired
  AccountMapper uMapper;

  @Autowired
  PasswordEncoder passwordEncoder;

  public void signupUser(Account user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    uMapper.insertUser(user);
  }
}
