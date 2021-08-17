package util;

import domain.User;

public class DemonstrateInformation {
    public void demonstratUserInfo(User user){
        int name = user.getName().length(),
                username = user.getUsername().length(),
                password = user.getPassword().length() ,
                birthdate = 10;
        int email;
        if(user.getEmail() == null){
            email = 15;
        }else{
            email = user.getEmail().length();
        }
        int phoneNumber;
        if(user.getPhoneNumber() == null){
            phoneNumber = 15;
        }else{
            phoneNumber = user.getPhoneNumber().length();
        }

        int dashLength = name+username+password+birthdate+email+phoneNumber+47;

        printUserHeader(dashLength,name,username,password,birthdate,email,phoneNumber);

        printUserData(dashLength,name,username,password,birthdate,email,phoneNumber,user);
    }
    private void printUserHeader(int dashLength,int name,
                                 int username,int password,
                                 int birthdate,int email,
                                 int phoneNumber){
        System.out.print("+");
        for(int i = 0 ; i < dashLength ; i++){
            System.out.print("-");
        }
        System.out.println("+");

        System.out.format("| %" + (-(name+6)) + "s","name");
        System.out.format("|%" + (-(username+15)) + "s","username");
        System.out.format("|%" + (-(password+5)) + "s","password");
        System.out.format("|%" + (-(birthdate+5)) + "s","birthdate");
        System.out.format("|%" + (-(email+5)) + "s","email");
        System.out.format("|%" + (-(phoneNumber+5)) + "s|","phonenumber");

        System.out.print("\n+");
        for(int i = 0 ; i < dashLength ; i++){
            System.out.print("-");
        }
        System.out.println("+");
    }

    private void printUserData(int dashLength,int name,
                               int username,int password,
                               int birthdate,int email,
                               int phoneNumber,User user){
        System.out.format("| %" + (-(name+6)) + "s",user.getName());
        System.out.format("|%" + (-(username+15)) + "s",user.getUsername());
        System.out.format("|%" + (-(password+5)) + "s",user.getPassword());
        System.out.format("|%" + (-(birthdate+5)) + "s",user.getBirthDate());
        System.out.format("|%" + (-(email+5)) + "s",user.getEmail());
        System.out.format("|%" + (-(phoneNumber+5)) + "s|",user.getPhoneNumber());

        System.out.print("\n+");
        for(int i = 0 ; i < dashLength ; i++){
            System.out.print("-");
        }
        System.out.println("+");
    }
}
