package oit.is.nksk.bavarder.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.nksk.bavarder.model.Account;
import oit.is.nksk.bavarder.model.AccountMapper;

@Controller
@RequestMapping("/test3")
public class ProfileController {

  @Autowired
  AccountMapper aMapper;

  @GetMapping("/moveprof")
  public String moveProf(@RequestParam String id, ModelMap model) {
    Account prof = aMapper.viewProfile(id);
    String name = prof.getUserName();
    Date birth = prof.getBirth();
    String gender = prof.getGender();
    String comment = prof.getComment();
    model.addAttribute("n", name);
    model.addAttribute("b", birth);
    model.addAttribute("g", gender);
    model.addAttribute("c", comment);
    return "profile.html";
  }
}
