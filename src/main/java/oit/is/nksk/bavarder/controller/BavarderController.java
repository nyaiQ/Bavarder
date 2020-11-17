package oit.is.nksk.bavarder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import java.security.Principal;
import oit.is.nksk.bavarder.model.Entry;

@Controller
public class BavarderController {

  @Autowired
  private Entry entry;

  @GetMapping("/chat")
  public String lec02(Principal prin, ModelMap model) {
    String name = prin.getName();
    this.entry.addUser(name);
    model.addAttribute("entry", this.entry);
    model.addAttribute("name", name);
    return "chat.html";
  }
  @PostMapping("/chat")
  public String chat(@RequestParam String name, ModelMap model) {
    model.addAttribute("name", name);
    return "chat.html";
  }

  @GetMapping("/send")
  public String send(@RequestParam String test, ModelMap model) {
    model.addAttribute("test", test);
    return "chat.html";
  }

}
