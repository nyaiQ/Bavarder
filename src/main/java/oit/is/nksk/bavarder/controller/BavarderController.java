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
import oit.is.nksk.bavarder.model.ChatMapper;
import oit.is.nksk.bavarder.model.Chat;

@Controller
@RequestMapping("/test1")
public class BavarderController {

  @Autowired
  AsyncChat asyncChat;

  @Autowired
  ChatMapper cMapper;

  @GetMapping("/chat")
  public String chat(Principal prin, ModelMap model) {
    String name = prin.getName();
    model.addAttribute("name", name);
    return "chat.html";
  }


  @PostMapping("/send")
  public String send(@RequestParam String message, String time,Principal prin, ModelMap model) {
    String name = prin.getName();
    int initiine = 0;
    asyncChat.syncSubmitChat(name, message, time,initiine);
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

  @GetMapping("/iine")
  public String iine(@RequestParam Integer id, Principal prin, ModelMap model) {
    String name = prin.getName();
    asyncChat.iineCount(id);
    model.addAttribute("name", name);
    return "chat.html";
  }

  @GetMapping("/eform")
  public String eform(@RequestParam Integer id, Principal prin, ModelMap model) {
    String name = prin.getName();
    Chat chat=cMapper.selectByID(id);
    model.addAttribute("name", name);
    model.addAttribute("chat", chat);
    return "chat.html";
  }

  @PostMapping("/edit")
  public String edit(@RequestParam Integer id, @RequestParam String message, Principal prin, ModelMap model) {
    String name = prin.getName();
    asyncChat.editMessage(id,message);
    model.addAttribute("name", name);
    return "chat.html";
  }

  @GetMapping("/delete")
  public String delete(@RequestParam Integer id, Principal prin, ModelMap model) {
    String name = prin.getName();
    cMapper.deleteByID(id);
    model.addAttribute("name", name);
    return "chat.html";
  }

}
