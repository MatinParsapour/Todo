package service.impl;

import base.repository.BaseEntityRepository;
import base.service.BaseEntityServiceImpl;
import domain.User;
import repository.UserRepository;
import service.UserService;
import util.ApplicationContext;

import java.util.Scanner;

public class UserServiceImpl extends BaseEntityServiceImpl<User,Long, UserRepository> implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public void logIn(){
        while(true){
            Scanner input = new Scanner(System.in);
            System.out.print("username : ");
            String username = input.next();
            System.out.print("password : ");
            String password = input.next();
            if(ApplicationContext.getUserServiceImpl().repository.checkUser(username,password)){
                System.out.println("<><><> You logged in <><><>");
                ApplicationContext.getDemonstration().choicesMenu();
            }else{
                System.out.println("Your username or password is incorrect");
                System.out.println("1.Back to main menu        2.Try again");
                int choice = new Scanner(System.in).nextInt();
                while(choice < 1 || choice > 2){
                    System.out.println("You should choose 1 or 2");
                    System.out.println("Try again");
                    choice = new Scanner(System.in).nextInt();
                }
                if(choice == 1){
                    break;
                }
            }
        }
    }
}
