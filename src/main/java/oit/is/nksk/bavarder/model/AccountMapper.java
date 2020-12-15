package oit.is.nksk.bavarder.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {
  @Insert("INSERT INTO user (username,password) VALUES (#{username},#{password});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertUser(Account user);

  @Select("SELECT username, password FROM user WHERE username = #{username}")
  Account findUser(String username);

}
