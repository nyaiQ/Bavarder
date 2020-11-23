package oit.is.nksk.bavarder.controller;

import java.util.ArrayList;
import java.util.Collections;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import oit.is.nksk.bavarder.model.Entry;
import oit.is.nksk.bavarder.model.Chat;
import oit.is.nksk.bavarder.model.ChatMapper;

@Controller
public class BavarderController {

  @Autowired
  private Entry entry;

  @Autowired
  private ChatMapper chatMapper;

  @GetMapping("/chat")
  public String lec02(Principal prin, ModelMap model) {
    String name = prin.getName();
    this.entry.addUser(name);
    model.addAttribute("entry", this.entry);
    model.addAttribute("name", name);
    ArrayList<Chat> chats = this.chatMapper.selectAllChat();
    Collections.reverse(chats);
    model.addAttribute("chats", chats);
    return "chat.html";
  }

  @PostMapping("/send")
  public String send(@RequestParam String message, Principal prin, ModelMap model) {
    String name = prin.getName();
    Chat chat = new Chat();
    chat.setUser(name);
    chat.setMessage(message);
    this.chatMapper.insertChat(chat);
    model.addAttribute("name", name);
    ArrayList<Chat> chats = this.chatMapper.selectAllChat();
    Collections.reverse(chats);
    model.addAttribute("chats", chats);
    return "chat.html";
  }

}
