package oit.is.nksk.bavarder.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import oit.is.nksk.bavarder.model.Account;
import oit.is.nksk.bavarder.model.AccountMapper;

@Service
public class ProfileService {
  @Autowired
  AccountMapper aMapper;

  @Transactional
  public void editProf(String userid, String name, String birth, String gender, String comment) {
    aMapper.editProf(userid, name, birth, gender, comment);
  }

  public Account myProf(String userid) {
    return aMapper.viewProfile(userid);
  }
}
