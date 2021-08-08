import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ToDoMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("<><><> Welcome <><><>");
        while(true){
            try{
                System.out.println("+-------------------+");
                System.out.println("|      1.Sign up    |");
                System.out.println("|      2.Log in     |");
                System.out.println("|      3. Exit      |");
                System.out.println("+-------------------+");
                System.out.print("Option : ");
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    // menu.signUp
                }else if(choice == 2){
                    // menu.logIn
                }else if(choice == 3){
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("Invalid entry");
            }
        }
    }
}
