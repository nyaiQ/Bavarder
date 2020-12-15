package oit.is.nksk.bavarder.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
@Mapper
public interface UserMapper {
  @Insert("INSERT INTO user (username,password) VALUES (#{username},#{password});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertUser(User user);

}
