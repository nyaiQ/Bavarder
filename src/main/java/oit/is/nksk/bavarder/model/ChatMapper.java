package oit.is.nksk.bavarder.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ChatMapper {

  @Select("SELECT * FROM chat")
  ArrayList<Chat> selectAllChat();

  /**
   * @param chat
   */
  @Insert("INSERT INTO chat (user,message,time,iine) VALUES (#{user},#{message},#{time},#{iine});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertChat(Chat chat);


  @Update("UPDATE CHAT SET iine = iine+1 WHERE ID = #{id}")
  void updateiinecount(int id);

}
