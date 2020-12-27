package oit.is.nksk.bavarder.collection;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import oit.is.nksk.bavarder.model.Account;

public class DbUserDetails extends User {
  private final Account account;

  public DbUserDetails(Account account, Collection<GrantedAuthority> authorities) {
        super(account.getUsername(), account.getPassword(), true, true, true, true, authorities);
    this.account = account;
  }

  public Account getAccount() {
    return account;
  }
}
