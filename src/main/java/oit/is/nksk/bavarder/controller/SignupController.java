package oit.is.nksk.bavarder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.nksk.bavarder.service.SignupService;
import oit.is.nksk.bavarder.model.Account;
import oit.is.nksk.bavarder.model.AccountMapper;

@Controller
@RequestMapping("/test2")
public class SignupController {

  @Autowired
  SignupService signup;

  @Autowired
  AccountMapper aMapper;

  @GetMapping("/signup")
  public String signup() {
    return "signup.html";
  }

  @PostMapping("/signup/register")
  public String register(@RequestParam String userid, @RequestParam String password,ModelMap model) {
    if (aMapper.isExist(userid)) {
      model.addAttribute("error", userid);
      return "signup.html";
    }
    else {
      Account user = new Account();
      user.setUserid(userid);
      user.setUsername(userid);
      user.setPassword(password);
      signup.signupUser(user);
      return "sucomp.html";
    }
  }

}
