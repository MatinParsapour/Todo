package service;

import entity.User;
import repository.TaskRepository;
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
    private TaskRepository taskRepository;

    public UserService(EntityManagerFactory entityManagerFactory,
                       UserRepository userRepository,
                       TaskRepository taskRepository){
        this.entityManagerFactory = entityManagerFactory;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
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
        logIn();
    }
    public void logIn(){
        TaskService taskService = new TaskService(taskRepository);
        System.out.println("<><><> Log In <><><>");
        boolean exit = false;
        while(!exit){
            System.out.println("       username     ");
            String username = input.next();
            System.out.println("       password     ");
            String password = input.next();
            boolean userAllow = userAllow(username,password);
            if(!userAllow){
                System.out.println("username or password is invalid");
                System.out.println("            1.try again        ");
                System.out.println("         2.back to main menu   ");
                int choice;
                while(true){
                    try{
                        choice = new Scanner(System.in).nextInt();
                        if(choice == 1 || choice == 2){
                            break;
                        }else{
                            System.out.println("you should choose 1 or 2");
                            System.out.println("try again");
                        }
                    }catch (InputMismatchException exception){
                        System.out.println("you should enter number");
                        System.out.println("try again");
                    }
                }if(choice == 2){
                    break;
                }
            }else{
                while(true){
                    try{
                        System.out.println("<><><><><><><> Welcome <><><><><><><>");
                        System.out.println("        1.See your activities        ");
                        System.out.println("          2.add an activity          ");
                        System.out.println("         3.change the status         ");
                        System.out.println("         4.change password           ");
                        System.out.println("         5.change username           ");
                        System.out.println("       6.change activity title       ");
                        System.out.println("       7.change activity content     ");
                        System.out.println("         8.back to main menu         ");
                        System.out.println("<><><><><><><><><><><><><><><><><><>");
                        int choice = new Scanner(System.in).nextInt();
                        if(choice == 1){
                            taskService.seeActivities(userRepository.findUser(username));
                        }else if(choice == 2){
                            taskService.addActivity(userRepository.findUser(username));
                        }else if(choice == 3){
                            taskService.changeStatus(userRepository.findUser(username));
                        }else if(choice == 4){
                            changePassword(userRepository.findUser(username));
                        }else if(choice == 5){
                            changeUsername(userRepository.findUser(username));
                        }else if(choice == 6){
                            taskService.changeTitle(userRepository.findUser(username));
                        }else if(choice == 7){
                            taskService.changeContent(userRepository.findUser(username));
                        }else if(choice == 8){
                            exit = true;
                            break;
                        }else{
                            System.out.println("Tou should choose between menu options");
                            System.out.println("Try again");
                        }
                    }catch (InputMismatchException exception){
                        System.out.println("You should enter number");
                        System.out.println("Try again");
                    }
                }

            }
        }
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
            boolean checkUsername = isDuplicate(username);
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
    private boolean isDuplicate(String username){
        List<User> users = userRepository.findAll();
        for(int counter = 0 ; counter < users.size() ; counter++){
            if(users.get(counter).getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
    private boolean userAllow(String username, String password){
        List<User> usersList = userRepository.findAll();
        for(int countUsers = 0 ; countUsers < usersList.size(); countUsers++){
            if(usersList.get(countUsers).getUsername().equals(username) && usersList.get(countUsers).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    private void changePassword(User user){
        System.out.println("Your password : " + user.getPassword());
        while(true){
            try{
                System.out.println("Do you want to change password");
                System.out.println("1.Yes                     2.No");
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    String password = password();
                    userRepository.updatePassword(password,user);
                    break;
                }
                else if(choice == 2){
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("You should enter number");
                System.out.println("Start over");
            }
        }
    }
    private void changeUsername(User user){
        System.out.println("Your username : " + user.getUsername());
        while(true){
            try{
                System.out.println("Do you want to change username");
                System.out.println("1.Yes                     2.No");
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    String username = username();
                    userRepository.updateUsername(username,user);
                    break;
                }
                else if(choice == 2){
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("You should enter number");
                System.out.println("Start over");
            }
        }
    }
}
