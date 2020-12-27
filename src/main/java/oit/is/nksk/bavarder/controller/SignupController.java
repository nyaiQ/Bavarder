package oit.is.nksk.bavarder.controller;

import java.util.regex.Pattern;

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
  public String register(@RequestParam String userid, @RequestParam String password, ModelMap model) {
    if (userid == null || userid.isEmpty() || password == null || password.isEmpty()) {
      model.addAttribute("error", "ユーザ名またはパスワードを入力してください");
      return "signup.html";
    }
    if (!Pattern.compile("^[a-zA-Z0-9]{1,8}$").matcher(userid).find()) {
      model.addAttribute("error", "ユーザ名は半角英数字1文字以上8文字以下で入力してください");
      return "signup.html";
    }
    if (!Pattern.compile("^[a-zA-Z0-9]{6,12}$").matcher(password).find()) {
      model.addAttribute("error", "パスワードは半角英数字6文字以上12文字以下で入力してください");
      return "signup.html";
    }
    if (aMapper.isExist(userid)) {
      model.addAttribute("error", userid + "は既に存在しています");
      return "signup.html";
    }
    Account user = new Account();
    user.setUserId(userid);
    user.setUserName(userid);
    user.setPassword(password);
    signup.signupUser(user);
    return "sucomp.html";
  }

}
