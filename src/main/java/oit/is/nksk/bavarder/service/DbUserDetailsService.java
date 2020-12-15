package oit.is.nksk.bavarder.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import oit.is.nksk.bavarder.model.Account;
import oit.is.nksk.bavarder.model.AccountMapper;
import oit.is.nksk.bavarder.collection.DbUserDetails;

@Service
public class DbUserDetailsService implements UserDetailsService {

  @Autowired
  AccountMapper aMapper;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = Optional.ofNullable(aMapper.findUser(username))
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new DbUserDetails(account, getAuthorities(account));
  }

  private Collection<GrantedAuthority> getAuthorities(Account account) {
    return AuthorityUtils.createAuthorityList("ROLE_USER");
  }

}
