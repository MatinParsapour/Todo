package service.impl;

import base.service.BaseEntityServiceImpl;
import domain.Task;
import domain.User;
import repository.TaskRepository;
import service.TaskService;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskServiceImpl extends BaseEntityServiceImpl<Task,Long, TaskRepository> implements TaskService {
    public TaskServiceImpl(TaskRepository repository) {
        super(repository);
    }

    @Override
    public void addActivity(User user) {
        String title = title();
        String content = content();
        Task task = new Task(title,content,new Date(),user);
        repository.saveOrUpdate(task);
    }
    private String title(){
        String title;
        while(true){
            try{
                System.out.print("Title : ");
                title = new Scanner(System.in).nextLine();
                System.out.println("1.Acceptable            2.Unacceptable");
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("You should enter number");
                System.out.println("Start over");
            }
        }
        return title;
    }

    private String content(){
        String content;
        while(true){
            try{
                System.out.print("Content : ");
                content = new Scanner(System.in).nextLine();
                System.out.println("1.Acceptable            2.Unacceptable");
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("You should enter number");
                System.out.println("Start over");
            }
        }
        return content;
    }
}
