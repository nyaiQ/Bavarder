package oit.is.nksk.bavarder.service;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.nksk.bavarder.model.Chat;
import oit.is.nksk.bavarder.model.ChatMapper;

@Service
public class AsyncChat {
  boolean dbUpdated = false;

  private final Logger logger = LoggerFactory.getLogger(AsyncChat.class);

  @Autowired
  ChatMapper cMapper;

  public ArrayList<Chat> syncShowChatList() {
    return cMapper.selectAllChat();
  }

  @Transactional
  public Chat syncSubmitChat(String name, String message, String time,int iine) {
    Chat chat = new Chat();
    chat.setUser(name);
    chat.setMessage(message);
    chat.setTime(time);
    chat.setiine(iine);
    this.cMapper.insertChat(chat);
    this.dbUpdated = true;
    return chat;
  }

  @Transactional
  public void iineCount(int id) {
    cMapper.updateiinecount(id);
    this.dbUpdated = true;
  }
  
  /**
   * @param emitter
   */
  @Async
  public void asyncShowChatList(SseEmitter emitter) {
    dbUpdated = true;
    try {
      while (true) {
        if (false == dbUpdated) {
          TimeUnit.MILLISECONDS.sleep(500);
          continue;
        }
        ArrayList<Chat> chatList = this.syncShowChatList();
        emitter.send(chatList);
        TimeUnit.MILLISECONDS.sleep(1000);
        dbUpdated = false;
      }
    } catch (Exception e) {
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
    System.out.println("asyncShowChatList complete");
  }

}
