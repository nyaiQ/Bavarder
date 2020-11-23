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

  @Insert("INSERT INTO chat (user,message) VALUES (#{user},#{message});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertChat(Chat chat);

}
