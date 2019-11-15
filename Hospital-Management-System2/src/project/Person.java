package project;
import java.util.regex.*;//Regular expression package, helps us easily check formats and patterns in Strings

public class Person {
private String Name, Nationality, Date_of_Birth, Mobile_Number, Email;
private int age;
public bool Date_of_Birth_Checker(String DoB){
  //To verify if the given String has the correct date format; returns true or false
  if(DoB.matches("Insert basic pattern here))
  {   
  }
  else
    return false;
}
public bool Mobile_Number_Checker(String number){
  //to verify if the given String has the correct phone number format; returns true or false
  return number.matches();
}
public bool Email_Checker(String mail){
  //to verify if the given String has the correct mail format; returns true or false
  return mail.matches();
}
}
