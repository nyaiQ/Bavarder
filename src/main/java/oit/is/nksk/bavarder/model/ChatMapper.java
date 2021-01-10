package oit.is.nksk.bavarder.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

@Mapper
public interface ChatMapper {

  @Select("SELECT * FROM chat")
  ArrayList<Chat> selectAllChat();

  @Select("SELECT * FROM chat WHERE ID=#{id}")
  Chat selectByID(int id);

  /**
   * @param chat
   */
  @Insert("INSERT INTO chat (user,message,time,iine) VALUES (#{user},#{message},#{time},#{iine});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertChat(Chat chat);

  @Update("UPDATE CHAT SET iine = iine+1 WHERE ID = #{id}")
  void updateiinecount(int id);

  @Update("UPDATE CHAT SET message = #{message} WHERE ID = #{id}")
  void updatemessage(int id, String message);

  @Delete("DELETE FROM chat WHERE ID = #{id}")
  boolean deleteByID(int id);

  @Select("SELECT * FROM chat WHERE USER LIKE '%' || #{user} || '%'")
  ArrayList<Chat> UserSearch(String user);

  @Select("SELECT * FROM chat WHERE TIME LIKE #{time} || '%'")
  ArrayList<Chat> TimeSearch(String time);

  @Select("SELECT * FROM chat WHERE MESSAGE LIKE '%' || #{message} || '%'")
  ArrayList<Chat> MessageSearch(String message);

}
