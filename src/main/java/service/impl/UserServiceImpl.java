package service.impl;

import base.service.BaseEntityServiceImpl;
import domain.User;
import repository.UserRepository;
import service.UserService;
import util.ApplicationContext;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Override
    public void signUp() {
        String name = name();
        String username = username();
        String password = password();
        User user = new User(name,username,password);
        ApplicationContext.getUserServiceImpl().repository.saveOrUpdate(user);
        logIn();
    }

    private String name(){
        String name;
        while(true){
            try{
                System.out.print("name : ");
                name = new Scanner(System.in).next();
                System.out.println("1.Acceptable            2.Unacceptable");
                int setName = new Scanner(System.in).nextInt();
                if(setName == 1){
                    break;
                }else{
                    System.out.println("Now try again");
                }
            }catch (InputMismatchException exception){
                System.out.println("You have to enter number");
                System.out.println("Start over");
            }
        }
        return name;
    }

    private String username(){
        String username;
        while(true){
            try{
                System.out.print("Username : ");
                username = new Scanner(System.in).next();
                if(!ApplicationContext.getUserServiceImpl().repository.exists(username)){
                    System.out.println("1.Acceptable              2.Unacceptable");
                    int choice = new Scanner(System.in).nextInt();
                    if(choice == 1){
                        break;
                    }else{
                        System.out.println("Now try again");
                    }
                }else{
                    System.out.println("Sorry!! there is a username like this");
                    System.out.println("try another username");
                }
            }catch (InputMismatchException exception){
                System.out.println("You should enter number");
                System.out.println("Start over");
            }
        }
        return username;
    }

    private String password(){
        String password;
        while(true){
            try{
                Pattern pattern = Pattern.compile("[0-9]{10}");
                System.out.print("Password : ");
                password = new Scanner(System.in).next();
                Matcher matcher  = pattern.matcher(password);
                while(!matcher.matches()){
                    System.out.println("You should enter a 10-digit password");
                    System.out.println("Try again");
                    password = new Scanner(System.in).next();
                    matcher = pattern.matcher(password);
                }
                System.out.println("1.Acceptable         2.Unacceptable");
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    break;
                }else{
                    System.out.println("Nowtry again");
                }
            }catch (InputMismatchException exception){
                System.out.println("You should enter number");
                System.out.println("Start over");
            }
        }
        return password;
    }
}
