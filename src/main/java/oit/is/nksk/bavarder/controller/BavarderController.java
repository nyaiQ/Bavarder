package oit.is.nksk.bavarder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BavarderController {

  @GetMapping("/chat")
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
