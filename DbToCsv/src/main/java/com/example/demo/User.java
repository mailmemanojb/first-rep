package com.example.demo;
public class User {
private String lastname;
private String firstname;
public User(){}
public User(String firstname, String lastname) {
this.firstname = firstname;
this.lastname = lastname;
}
public String getFirstname() {
return firstname;
}
public void setFirstname(String firstname) {
this.firstname = firstname;
}
public String getLastname() {
return lastname;
}
public void setLastname(String lastname) {
this.lastname = lastname;
}
@Override
public String toString() {
return "People [firstname=" + firstname + ", lastname=" + lastname + "]";
}
}
