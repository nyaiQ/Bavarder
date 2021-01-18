package oit.is.nksk.bavarder.model;

public class Chat {
  int id;
  String user;
  String username;
  String message;
  String time;
  int iine;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUser() {
    return user;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getTime(){
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public int getiine() {
    return iine;
  }

  public void setiine(int iine) {
    this.iine = iine;
  }
}
