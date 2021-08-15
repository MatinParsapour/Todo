package service.impl;

import base.service.BaseEntityServiceImpl;
import domain.Task;
import domain.User;
import repository.TaskRepository;
import service.TaskService;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
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

    @Override
    public void changeTitle(User user) {
        while(true){
            List<Task> tasks = repository.findUserActivities(user);
            for(Task task : tasks){
                System.out.println("Title : " + task.getTitle());
                System.out.println("Content : " + task.getContent());
                System.out.println("Status : " + task.getStatus());
            }
            try{
                System.out.println("1.Change title          2.Back to main menu");
                int choice = new Scanner(System.in).nextInt();
                if(choice == 2){
                    break;
                }else if(choice == 1){
                    System.out.println("Enter the title of activity you want to change title");
                    String title = new Scanner(System.in).nextLine();
                    if(repository.checkTitle(title,user)){
                        Task task = repository.findActivity(title,user);
                        System.out.print("New title : ");
                        String newTitle = new Scanner(System.in).nextLine();
                        task.setTitle(newTitle);
                        repository.saveOrUpdate(task);
                    }else{
                        System.out.println("This title isn't belong to one of your activities");
                        System.out.println("1.Back to main menu                   2.Try again");
                        int wrongTitle = new Scanner(System.in).nextInt();
                        if(wrongTitle == 1){
                            break;
                        }
                    }
                }
            }catch (InputMismatchException exception){
                System.out.println("You should enter number");
                System.out.println("Start over");
            }
        }
    }
}
