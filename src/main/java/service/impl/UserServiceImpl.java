package service.impl;

import base.service.BaseEntityServiceImpl;
import domain.User;
import repository.UserRepository;
import service.UserService;
import util.ApplicationContext;

import java.time.LocalDate;
import java.util.Date;
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
        System.out.println("<><><> Log in <><><>");
        boolean exit = false;
        while(!exit){
            try{
                Scanner input = new Scanner(System.in);
                System.out.print("username : ");
                String username = input.next();
                System.out.print("password : ");
                String password = input.next();
                if(repository.checkUser(username,password)){
                    Long userId = repository.findId(username);
                    System.out.println("<><><> You logged in <><><>");
                    while(true) {
                        ApplicationContext.getDemonstration().choicesMenu();
                        int choice = new Scanner(System.in).nextInt();
                        if (choice == 1) {
                            //see your activities
                        } else if (choice == 2) {
                            ApplicationContext.getTaskServiceImpl().addActivity(findById(userId));
                        } else if (choice == 3) {
                            ApplicationContext.getTaskServiceImpl().changeStatus(findById(userId));
                        } else if (choice == 4) {
                            ApplicationContext.getTaskServiceImpl().changeTitle(findById(userId));
                        } else if (choice == 5) {
                            ApplicationContext.getTaskServiceImpl().changeContent(findById(userId));
                        } else if (choice == 6) {
                            profile(userId);
                        }else if (choice == 7) {
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
        System.out.println("<><><> Sign up <><><>");
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

    @Override
    public void profile(Long id) {
        User user = findById(id);
        while(true){
            try{
                System.out.println("Your profile");
                System.out.println("1.Name : " + user.getName());
                System.out.println("2.Username : " + user.getUsername());
                System.out.println("3.Password : " + user.getPassword());
                System.out.println("4.Birth Date : " + user.getBirthDate());
                System.out.println("5.Email : " + user.getEmail());
                System.out.println("6.Phone number : " + user.getPhoneNumber());
                System.out.println("1.change profile            2.back to main menu");
                int choice = new Scanner(System.in).nextInt();
                if(choice == 2){
                    break;
                }else if (choice == 1){
                    System.out.println("Which one?");
                    int changeProfile = new Scanner(System.in).nextInt();
                    switch (changeProfile) {
                        case 1:
                            String name = name();
                            user.setName(name);
                            repository.saveOrUpdate(user);
                            System.out.println("!!! Your name changed !!!");
                            break;
                        case 2:
                            String username = username();
                            user.setUsername(username);
                            repository.saveOrUpdate(user);
                            System.out.println("!!! Your username changed !!!");
                            break;
                        case 3:
                            String password = password();
                            user.setPassword(password);
                            repository.saveOrUpdate(user);
                            System.out.println("!!! Your password changed !!!");
                            break;
                        case 4:
                            LocalDate birthDate = birthDate();
                            user.setBirthDate(birthDate);
                            repository.saveOrUpdate(user);
                            System.out.println("!!! Your birth Date changed !!!");
                            break;
                        case 5:
                            String email = email();
                            user.setEmail(email);
                            repository.saveOrUpdate(user);
                            System.out.println("!!! Your email changed !!!");
                            break;
                        case 6:
                            String phoneNumber = phoneNumber();
                            user.setPhoneNumber(phoneNumber);
                            repository.saveOrUpdate(user);
                            System.out.println("!!! Your phone number changed !!!");
                            break;
                    }
                }else{
                    System.out.println("You should choose 1 or 2");
                    System.out.println("Try again");
                }
            }catch (InputMismatchException exception){
                System.out.println("You should enter number");
                System.out.println("Try again");
            }
        }
    }

    private LocalDate birthDate(){
        LocalDate date;
        while(true){
            try{
                System.out.print("Year : ");
                int year = new Scanner(System.in).nextInt();
                System.out.print("Month : ");
                int month = new Scanner(System.in).nextInt();
                while(month < 1 || month > 12){
                    System.out.println("This is not a valid number for month");
                    System.out.println("Try again");
                    month = new Scanner(System.in).nextInt();
                }
                System.out.print("Day : ");
                int day = new Scanner(System.in).nextInt();
                while(day < 1 || day > 31){
                    System.out.println("This is not a valid number for day");
                    System.out.println("Try again");
                    day = new Scanner(System.in).nextInt();
                }
                date = LocalDate.of(year,month,day);
                if(LocalDate.now().isAfter(date)){
                    break;
                }else{
                    System.out.println("This is not a valid date for your birthday");
                    System.out.println("Try again");
                }
            }catch (InputMismatchException exception){
                System.out.println("You should enter number");
                System.out.println("Try again");
            }
        }
        return date;
    }

    private String email(){
        Pattern validEmail = Pattern.compile( "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[\\a-zA-Z]{2,6}");
        System.out.print("Email : ");
        String email = new Scanner(System.in).next();
        Matcher machEmail = validEmail.matcher(email);
        while(!machEmail.matches()){
            System.out.println("this is not a valid email");
            System.out.println("Try again");
            email = new Scanner(System.in).next();
            machEmail = validEmail.matcher(email);
        }
        return email;
    }

    private String phoneNumber(){
        Pattern validPhoneNumber = Pattern.compile("[0]{1}[9]{1}[0-9]{9}");
        System.out.println("Enter your full phone number");
        String phoneNumber = new Scanner(System.in).next();
        Matcher matchPhoneNumber = validPhoneNumber.matcher(phoneNumber);
        while(!matchPhoneNumber.matches()){
            System.out.println("This is not a valid phone number");
            System.out.println("Try again");
            phoneNumber = new Scanner(System.in).next();
            matchPhoneNumber = validPhoneNumber.matcher(phoneNumber);
        }
        return phoneNumber;
    }
}
