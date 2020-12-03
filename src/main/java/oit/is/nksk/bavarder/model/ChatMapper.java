package oit.is.nksk.bavarder.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChatMapper {

  @Select("SELECT * FROM chat")
  ArrayList<Chat> selectAllChat();

  /**
   * @param chat
   */
  @Insert("INSERT INTO chat (user,message,time) VALUES (#{user},#{message},#{time});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertChat(Chat chat);

}
