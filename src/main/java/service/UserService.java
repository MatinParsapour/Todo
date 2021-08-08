package service;

import entity.User;
import repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    static Scanner input = new Scanner(System.in);
    private EntityManagerFactory entityManagerFactory;
    private UserRepository userRepository;

    public UserService(EntityManagerFactory entityManagerFactory,UserRepository userRepository){
        this.entityManagerFactory = entityManagerFactory;
        this.userRepository = userRepository;
    }

    public void signUp(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("++++++ sign up +++++");
        entityManager.getTransaction().begin();
        String name = name();
        String username = username();
        String password = password();
        User user = new User(name,username,password);
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }
    private String name(){
        String name;
        while (true){
            try{
                System.out.println("     name     ");
                name = input.next();
                System.out.println("1.Acceptable        2.Unacceptable");
                int choice = input.nextInt();
                if(choice == 1){
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("You should enter number");
                System.out.println("Start over");
            }
        }
        return name;
    }
    private String username(){
        String username;
        while (true) {
            System.out.println("     username   ");
            username = new Scanner(System.in).next();
            boolean checkUsername = isDouplicate(username);
            if(checkUsername){
                System.out.println("There is a username like this");
                System.out.println("try again");
            }else{
                break;
            }
        }
        return username;
    }
    private String password(){
        Pattern pattern = Pattern.compile("[0-9]{10}");
        String password;
        while (true) {
            System.out.println("     password    ");
            password = new Scanner(System.in).next();
            Matcher matcher = pattern.matcher(password);
            while(!matcher.matches()){
                System.out.println("You should enter a 10-digit password");
                System.out.println("Try again");
                password = new Scanner(System.in).next();
                matcher = pattern.matcher(password);
            }
            break;
        }
        return password;
    }
    private boolean isDouplicate(String username){
        List<User> users = userRepository.findAll();
        for(int counter = 0 ; counter < users.size() ; counter++){
            if(users.get(counter).getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
}
