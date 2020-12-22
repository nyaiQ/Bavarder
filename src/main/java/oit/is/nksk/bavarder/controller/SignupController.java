package oit.is.nksk.bavarder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.nksk.bavarder.service.SignupService;
import oit.is.nksk.bavarder.model.Account;

@Controller
@RequestMapping("/test2")
public class SignupController {

  @Autowired
  SignupService signup;

  @GetMapping("/signup")
  public String signup() {
    return "signup.html";
  }

  @PostMapping("/signup/register")
  public String register(@RequestParam String username, @RequestParam String password) {
    Account user = new Account();
    user.setUsername(username);
    user.setPassword(password);
    signup.signupUser(user);
    return "sucomp.html";
  }

}
