package service;

import entity.Tasks;
import entity.User;
import repository.TaskRepository;
import repository.UserRepository;


import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    public void addActivity(User user){
        System.out.println("<><><> add an activity <><><>");
        String title = title();
        String body = body();
        LocalDate creationDate = LocalDate.now();
        Tasks task = new Tasks(title,body,creationDate,user);
        taskRepository.addActivity(task);
    }
    private String title(){
        String title;
        while (true) {
            try{
                System.out.println("   Title   ");
                title = new Scanner(System.in).nextLine();
                System.out.println("1.Acceptable     2.Unacceptable");
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1)
                    break;
                else if(choice == 2){
                    System.out.println("Now enter another title");
                }else{
                    System.out.println("you should choose 1 or 2");
                }
            }catch (InputMismatchException exception){
                System.out.println("You should enter number");
                System.out.println("Start over");
            }
        }
        return title;
    }
    private String body(){
        String body;
        while(true){
            try{
                System.out.println("    Task   ");
                body = new Scanner(System.in).nextLine();
                System.out.println("1.Acceptable         2.Unacceptable");
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    break;
                }else if(choice == 2){
                    System.out.println("Now edit your task content");
                }else{
                    System.out.println("You should choose 1 or 2");
                }
            }catch (InputMismatchException exception){
                System.out.println("You should enter number");
                System.out.println("Try again");
            }
        }
        return body;
    }
}
