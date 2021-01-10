package oit.is.nksk.bavarder.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oit.is.nksk.bavarder.model.Account;
import oit.is.nksk.bavarder.model.AccountMapper;

@Service
public class ResultService {
  @Autowired
  AccountMapper aMapper;

  public Account searchProf(String id) {
    ArrayList<Account> prof = new ArrayList<Account>();
    prof = aMapper.viewProfile(id);
    return prof;
  }
}
