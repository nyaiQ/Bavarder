package oit.is.nksk.bavarder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test2")
public class SignupController {

  @GetMapping("/signup")
  public String signup() {
    return "signup.html";
  }

}
