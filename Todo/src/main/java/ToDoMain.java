import service.UserService;
import util.HibernateUtil;

import javax.persistence.EntityManagerFactory;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ToDoMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

        UserRepository userRepository = new UserRepository(entityManagerFactory);
        TaskRepository taskRepository = new TaskRepository(entityManagerFactory);


        UserService userService = new UserService(entityManagerFactory, userRepository, taskRepository);

        System.out.println("<><><> Welcome <><><>");
        while (true) {
            try {
                System.out.println("+-------------------+");
                System.out.println("|      1.Sign up    |");
                System.out.println("|      2.Log in     |");
                System.out.println("|      3. Exit      |");
                System.out.println("+-------------------+");
                System.out.print("Option : ");
                int choice = new Scanner(System.in).nextInt();
                if (choice == 1) {
                    userService.signUp();
                } else if (choice == 2) {
                    userService.logIn();
                } else if (choice == 3) {
                    System.out.println("Hope to see you soon🧡🧡");
                    break;
                }
            } catch (InputMismatchException exception) {
                System.out.println("Invalid entry");
            }
        }
    }
}
