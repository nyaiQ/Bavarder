package oit.is.nksk.bavarder.controller;

import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import oit.is.nksk.bavarder.model.Account;
import oit.is.nksk.bavarder.model.AccountMapper;
import oit.is.nksk.bavarder.service.ProfileService;

@Controller
@RequestMapping("/test3")
public class ProfileController {

  private final Logger logger = LoggerFactory.getLogger(ProfileController.class);

  @Autowired
  ProfileService pservice;

  @Autowired
  AccountMapper aMapper;

  @GetMapping("/moveprof")
  public String moveProf(@RequestParam String id, ModelMap model) {
    Account prof = aMapper.viewProfile(id);
    String name = prof.getUserName();
    String birth = prof.getBirth();
    String gender = prof.getGender();
    String comment = prof.getComment();
    model.addAttribute("n", name);
    model.addAttribute("b", birth);
    model.addAttribute("g", gender);
    model.addAttribute("c", comment);
    return "profile.html";
  }

  @PostMapping("/editprof")
  public String profedit(@RequestParam String name, @RequestParam String birth,
  @RequestParam String gender, @RequestParam String comment, Principal prin, ModelMap model) {
    pservice.editProf(prin.getName(), name, birth, gender, comment);
    return "profile.html";
  }

  @GetMapping("/eprofform")
  public String eprofform(Principal prin, ModelMap model) {
    logger.info(prin.getName());
    Account myProf = pservice.myProf(prin.getName());
    model.addAttribute("profile", myProf);
    logger.info(myProf.getUserId());
    return "profile.html";
  }

}
