package service.impl;

import base.repository.BaseEntityRepository;
import base.service.BaseEntityServiceImpl;
import domain.User;
import repository.UserRepository;
import service.UserService;
import util.ApplicationContext;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserServiceImpl extends BaseEntityServiceImpl<User,Long, UserRepository> implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public void logIn(){
        boolean exit = false;
        while(!exit){
            try{
                Scanner input = new Scanner(System.in);
                System.out.print("username : ");
                String username = input.next();
                System.out.print("password : ");
                String password = input.next();
                if(ApplicationContext.getUserServiceImpl().repository.checkUser(username,password)){
                    System.out.println("<><><> You logged in <><><>");
                    while(true){
                        ApplicationContext.getDemonstration().choicesMenu();
                        int choice = new Scanner(System.in).nextInt();
                        if(choice == 1){
                            //see your activities
                        }else if (choice == 2){
                            //add an activity
                        }else if (choice == 3){
                            //change status
                        }else if(choice == 4){
                            //change password
                        }else if(choice == 5){
                            //change username
                        }else if(choice == 6){
                            //change activity title
                        }else if(choice == 7){
                            //change activity content
                        }else if(choice == 8){
                            exit = true;
                            break;
                        }
                    }
                }else{
                    System.out.println("Your username or password is incorrect");
                    System.out.println("1.Back to main menu        2.Try again");
                    int failedLogInChoice = new Scanner(System.in).nextInt();
                    while(failedLogInChoice < 1 || failedLogInChoice > 2){
                        System.out.println("You should choose 1 or 2");
                        System.out.println("Try again");
                        failedLogInChoice = new Scanner(System.in).nextInt();
                    }
                    if(failedLogInChoice == 1){
                        exit = true;
                    }
                }
            }catch (InputMismatchException exception){
                System.out.println("You should enter number");
                System.out.println("Start over");
            }
        }
    }
}
