package oit.is.nksk.bavarder.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import org.springframework.beans.factory.annotation.Autowired;

import oit.is.nksk.bavarder.service.AsyncChat;
import oit.is.nksk.bavarder.service.ResultService;
import oit.is.nksk.bavarder.model.ChatMapper;
import oit.is.nksk.bavarder.model.Chat;
import oit.is.nksk.bavarder.model.Account;

@Controller
@RequestMapping("/test1")
public class BavarderController {

  @Autowired
  AsyncChat asyncChat;

  @Autowired
  ChatMapper cMapper;

  @Autowired
  ResultService resultservice;

  @GetMapping("/chat")
  public String chat(Principal prin, ModelMap model) {
    String name = prin.getName();
    model.addAttribute("name", name);
    return "chat.html";
  }

  @PostMapping("/send")
  public String send(@RequestParam String message, String time, Principal prin, ModelMap model) {
    String name = prin.getName();
    if (!message.matches("[\\s]+") && message != null && !message.isEmpty()) {
      asyncChat.syncSubmitChat(name, message, time, 0);
    }
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
    Chat chat = cMapper.selectByID(id);
    model.addAttribute("name", name);
    model.addAttribute("chat", chat);
    return "chat.html";
  }

  @PostMapping("/edit")
  public String edit(@RequestParam Integer id, @RequestParam String message, Principal prin, ModelMap model) {
    String name = prin.getName();

    if (name.equals(cMapper.selectByID(id).getUser())) {
      asyncChat.editMessage(id, message);
    }

    model.addAttribute("name", name);
    return "chat.html";
  }

  @GetMapping("/delete")
  public String delete(@RequestParam Integer id, Principal prin, ModelMap model) {
    String name = prin.getName();

    if (name.equals(cMapper.selectByID(id).getUser())) {
      cMapper.deleteByID(id);
    }

    model.addAttribute("name", name);
    return "chat.html";
  }

  @PostMapping("/search")
  public String search(@RequestParam String keyword, @RequestParam String action, Principal prin, ModelMap model) {
    if (action.equals("ユーザ検索")) {
      model.addAttribute("results", resultservice.searchProf(keyword));
    }
    if (action.equals("日時検索")) {
      model.addAttribute("results", cMapper.TimeSearch(keyword));
    }
    if (action.equals("メッセージ検索")) {
      model.addAttribute("results", cMapper.MessageSearch(keyword));
    }
    String name = prin.getName();
    model.addAttribute("name", name);
    model.addAttribute("keyword", keyword);
    model.addAttribute("action", action);
    return "result.html";
  }

  @GetMapping("/rchat")
  public String returnchat(Principal prin, ModelMap model) {
    String name = prin.getName();
    model.addAttribute("name", name);
    return "chat.html";
  }

  @GetMapping("/resultiine")
  public String resultiine(@RequestParam Integer id, @RequestParam String keyword, @RequestParam String action,
      Principal prin, ModelMap model) {
    asyncChat.iineCount(id);
    ArrayList<Chat> results = new ArrayList<Chat>();
    if (action.equals("日時検索")) {
      results = cMapper.TimeSearch(keyword);
    }
    if (action.equals("メッセージ検索")) {
      results = cMapper.MessageSearch(keyword);
    }
    String name = prin.getName();
    model.addAttribute("name", name);
    model.addAttribute("keyword", keyword);
    model.addAttribute("results", results);
    model.addAttribute("action", action);
    return "result.html";
  }

  @GetMapping("/resultform")
  public String resultform(@RequestParam Integer id, @RequestParam String keyword, @RequestParam String action,
      Principal prin, ModelMap model) {
    String name = prin.getName();
    Chat chat = cMapper.selectByID(id);
    ArrayList<Chat> results = new ArrayList<Chat>();
    if (action.equals("日時検索")) {
      results = cMapper.TimeSearch(keyword);
    }
    if (action.equals("メッセージ検索")) {
      results = cMapper.MessageSearch(keyword);
    }
    model.addAttribute("name", name);
    model.addAttribute("chat", chat);
    model.addAttribute("keyword", keyword);
    model.addAttribute("results", results);
    model.addAttribute("action", action);
    return "result.html";
  }

  @PostMapping("/resultedit")
  public String resultedit(@RequestParam Integer id, @RequestParam String keyword, @RequestParam String action,
      @RequestParam String message, Principal prin, ModelMap model) {
    String name = prin.getName();

    if (name.equals(cMapper.selectByID(id).getUser())) {
      asyncChat.editMessage(id, message);
    }

    ArrayList<Chat> results = new ArrayList<Chat>();
    if (action.equals("日時検索")) {
      results = cMapper.TimeSearch(keyword);
    }
    if (action.equals("メッセージ検索")) {
      results = cMapper.MessageSearch(keyword);
    }
    model.addAttribute("name", name);
    model.addAttribute("keyword", keyword);
    model.addAttribute("results", results);
    model.addAttribute("action", action);
    return "result.html";
  }

  @GetMapping("/resultdelete")
  public String resultdelete(@RequestParam Integer id, @RequestParam String keyword, @RequestParam String action,
      Principal prin, ModelMap model) {
    String name = prin.getName();

    if (name.equals(cMapper.selectByID(id).getUser())) {
      cMapper.deleteByID(id);
    }

    ArrayList<Chat> results = new ArrayList<Chat>();
    if (action.equals("日時検索")) {
      results = cMapper.TimeSearch(keyword);
    }
    if (action.equals("メッセージ検索")) {
      results = cMapper.MessageSearch(keyword);
    }
    model.addAttribute("name", name);
    model.addAttribute("keyword", keyword);
    model.addAttribute("results", results);
    model.addAttribute("action", action);
    return "result.html";
  }

  @GetMapping("/showprofile")
  public String showprofile(@RequestParam String id, @RequestParam String keyword, @RequestParam String action,
      Principal prin, ModelMap model) {
    String name = prin.getName();
    Account results = resultservice.viewProf(id);
    model.addAttribute("name", name);
    model.addAttribute("keyword", keyword);
    model.addAttribute("results", results);
    model.addAttribute("action", action);
    model.addAttribute("n", results.getUserName());
    model.addAttribute("b", results.getBirth());
    model.addAttribute("g", results.getGender());
    model.addAttribute("c", results.getComment());
    return "profile.html";
  }

  @GetMapping("/rsearch")
  public String returnsearch(@RequestParam String keyword, @RequestParam String action, Principal prin,
      ModelMap model) {
    String name = prin.getName();
    model.addAttribute("results", resultservice.searchProf(keyword));
    model.addAttribute("name", name);
    model.addAttribute("keyword", keyword);
    model.addAttribute("action", action);
    return "result.html";
  }
}
