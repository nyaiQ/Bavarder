package oit.is.nksk.bavarder.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {
  @Insert("INSERT INTO (userid,username,password,birth,gender,comment) VALUES (#{userid},#{username},#{password},#{birth},#{gender},#{comment});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertUser(Account user);

  @Select("SELECT * FROM user WHERE userid = #{id}")
  Account findUser(String id);

  @Select("SELECT EXISTS (SELECT * FROM user WHERE userid = #{id})")
  boolean isExist(String id);

  @Select("SELECT * FROM user WHERE userid = #{id}")
  Account viewProfile(String id);

}
