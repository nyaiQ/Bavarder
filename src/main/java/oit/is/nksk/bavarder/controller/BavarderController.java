package oit.is.nksk.bavarder.controller;

import java.util.ArrayList;
import java.util.Collections;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.beans.factory.annotation.Autowired;

import oit.is.nksk.bavarder.model.Entry;
import oit.is.nksk.bavarder.service.AsyncChat;
import oit.is.nksk.bavarder.model.Chat;

@Controller
@RequestMapping("/test1")
public class BavarderController {

  @Autowired
  private Entry entry;

  @Autowired
  AsyncChat asyncChat;

  @GetMapping("/chat")
  public String lec02(Principal prin, ModelMap model) {
    String name = prin.getName();
    this.entry.addUser(name);
    model.addAttribute("entry", this.entry);
    model.addAttribute("name", name);
    ArrayList<Chat> chats = asyncChat.syncShowChatList();
    Collections.reverse(chats);
    model.addAttribute("chats", chats);
    return "chat.html";
  }

  @PostMapping("/send")
  public String send(@RequestParam String message, Principal prin, ModelMap model) {
    String name = prin.getName();
    asyncChat.syncSubmitChat(name, message);
    model.addAttribute("name", name);
    ArrayList<Chat> chats = asyncChat.syncShowChatList();
    Collections.reverse(chats);
    model.addAttribute("chats", chats);
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
