package oit.is.nksk.bavarder.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.beans.factory.annotation.Autowired;

import oit.is.nksk.bavarder.service.AsyncChat;

@Controller
@RequestMapping("/test1")
public class BavarderController {

  @Autowired
  AsyncChat asyncChat;

  @GetMapping("/chat")
  public String chat(Principal prin, ModelMap model) {
    String name = prin.getName();
    model.addAttribute("name", name);
    return "chat.html";
  }

  @PostMapping("/send")
  public String send(@RequestParam String message, Principal prin, ModelMap model) {
    String name = prin.getName();
    asyncChat.syncSubmitChat(name, message);
    model.addAttribute("name", name);
    return "chat.html";
  }

  /**
   * @return
   */
  @GetMapping("pushChat")
  public SseEmitter pushChat() {
    final SseEmitter sseEmitter = new SseEmitter();
    this.asyncChat.asyncShowChatList(sseEmitter);
    return sseEmitter;
  }

}
