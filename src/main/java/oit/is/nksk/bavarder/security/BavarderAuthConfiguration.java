package oit.is.nksk.bavarder.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Sample3AuthConfiguration
 */
@Configuration
@EnableWebSecurity
public class BavarderAuthConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  UserDetailsService userDetailsService;

  /**
   * 誰がログインできるか(認証処理)
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    /*
     * // $ sshrun htpasswd -nbBC 10 user1 pAssw0rd
     * auth.inMemoryAuthentication().withUser("user1")
     * .password("$2y$10$rJ9yqGht2W96MdIJICRQQOuUiYrt2eDokKnDuZZof2DPs83PN6QdC").
     * roles("USER"); // $ sshrun htpasswd -nbBC 10 user2 User2
     * auth.inMemoryAuthentication().withUser("user2")
     * .password("$2y$10$OSgKQ7mgvAOXyo0SW1qgbOYHm8vcgujVL04PqNOKARz2CLFArNYUu").
     * roles("USER"); // $ sshrun htpasswd -nbBC 10 user3 User3
     * auth.inMemoryAuthentication().withUser("user3")
     * .password("$2y$10$/Q07Xpngjsa.Y.xzG6aSaOsM7fX7EZFhmRLMTqtbtruLw6nMkH29G").
     * roles("USER"); // $ sshrun htpasswd -nbBC 10 User4 User4
     * auth.inMemoryAuthentication().withUser("user4")
     * .password("$2y$10$uHv3aqlHHAjsSkg1JWtTEeKCsPa6OIl4pcPLmm6MDMqqH1ukQ53w2").
     * roles("ADMIN"); }
     */
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * 認証されたユーザがどこにアクセスできるか（認可処理）
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // Spring Securityのフォームを利用してログインを行う
    http.formLogin();

    // antMatchers().authenticated がantMatchersへのアクセスに認証を行うことを示す
    http.authorizeRequests().antMatchers("/test1/**").authenticated();

    // 以下2行はh2-consoleを利用するための設定なので，開発が完了したらコメントアウトすることが望ましい
    http.csrf().disable();
    http.headers().frameOptions().disable();

    // Spring Securityの機能を利用してログアウト．
    http.logout().logoutSuccessUrl("/");
  }

}
