package oit.is.nksk.bavarder.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {
  @Insert("INSERT INTO user (userid,username,password) VALUES (#{userid},#{username},#{password});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertUser(Account user);

  @Select("SELECT userid,username,password FROM user WHERE userid = #{userid}")
  Account findUser(String userid);

  @Select("SELECT EXISTS (SELECT * FROM user WHERE userid = #{userid})")
  boolean isExist(String userid);
}
