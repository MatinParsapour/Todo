import repository.UserRepository;
import service.UserService;
import service.impl.UserServiceImpl;
import util.ApplicationContext;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ToDoMain {
    public static void main(String[] args) {
        UserServiceImpl userServiceImpl = ApplicationContext.getUserServiceImpl();
        System.out.println("🧡🧡🧡 Welcome 🧡🧡🧡");
        while(true){
            try{
                System.out.println("-----------------------");
                System.out.println("       1.Sign Up       ");
                System.out.println("        2.Log In       ");
                System.out.println("         3.Exit        ");
                System.out.println("-----------------------");
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    userServiceImpl.logIn();
                }else if(choice == 2){
                    //logIn
                }else if(choice == 3){
                    break;
                }else{
                    System.out.println("You should choose between menu options");
                    System.out.println("Try again");
                }
            }catch (InputMismatchException exception){
                System.out.println("You should enter number");
                System.out.println("Try again");
            }
        }
    }
}
