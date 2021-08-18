package service.impl;

import base.service.BaseEntityServiceImpl;
import domain.Task;
import domain.User;
import repository.TaskRepository;
import service.TaskService;
import util.ApplicationContext;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TaskServiceImpl extends BaseEntityServiceImpl<Task, Long, TaskRepository> implements TaskService {
    public TaskServiceImpl(TaskRepository repository) {
        super(repository);
    }

    @Override
    public void addActivity(User user) {
        String title = title();
        String content = content();
        String description = "create task with title " + title;
        Task task = new Task(title, content, new Date(), user,new Date(),description);
        repository.saveOrUpdate(task);
        System.out.println("This activity added");
    }

    private String title() {
        String title;
        while (true) {
            try {
                System.out.print("Title : ");
                title = new Scanner(System.in).nextLine();
                System.out.println("1.Acceptable            2.Unacceptable");
                int choice = new Scanner(System.in).nextInt();
                if (choice == 1) {
                    break;
                }
            } catch (InputMismatchException exception) {
                System.out.println("You should enter number");
                System.out.println("Start over");
            }
        }
        return title;
    }

    private String content() {
        String content;
        while (true) {
            try {
                System.out.print("Content : ");
                content = new Scanner(System.in).nextLine();
                System.out.println("1.Acceptable            2.Unacceptable");
                int choice = new Scanner(System.in).nextInt();
                if (choice == 1) {
                    break;
                }
            } catch (InputMismatchException exception) {
                System.out.println("You should enter number");
                System.out.println("Start over");
            }
        }
        return content;
    }

    @Override
    public void changeTitle(User user) {
        List<Task> tasks = repository.findUserActivities(user);
        if(tasks.size() != 0){
            while (true) {
                ApplicationContext.getDemonstrateInformation().demonstrateTaskInfo(tasks);
                try {
                    System.out.println("1.Change title          2.Back to main menu");
                    int choice = new Scanner(System.in).nextInt();
                    if (choice == 1) {
                        System.out.print("Enter the title of activity you want to change title : ");
                        String title = new Scanner(System.in).nextLine();
                        if (repository.checkTitle(title, user)) {
                            Task task = repository.findActivity(title, user);
                            System.out.print("New title : ");
                            String newTitle = new Scanner(System.in).nextLine();
                            task.setTitle(newTitle);
                            task.setLastUpdate(new Date());
                            task.setDescription("change title from " + title + " to " + newTitle);
                            repository.saveOrUpdate(task);
                        } else {
                            System.out.println("This title isn't belong to one of your activities");
                            System.out.println("Try again");
                        }
                    } else if (choice == 2) {
                        break;
                    } else {
                        System.out.println("you should choose 1 or 2");
                        System.out.println("Try again");
                    }
                } catch (InputMismatchException exception) {
                    System.out.println("You should enter number");
                    System.out.println("Start over");
                }
            }
        }else{
            System.out.println("You don't have any task yet");
        }
    }

    @Override
    public void changeContent(User user) {
        List<Task> tasks = repository.findUserActivities(user);
        if(tasks.size() != 0){
            while (true) {
                ApplicationContext.getDemonstrateInformation().demonstrateTaskInfo(tasks);
                try {
                    System.out.println("1.Change content              2.Back to main menu");
                    int choice = new Scanner(System.in).nextInt();
                    if (choice == 1) {
                        System.out.print("Enter title of activity you want to change content : ");
                        String title = new Scanner(System.in).nextLine();
                        if (repository.checkTitle(title, user)) {
                            Task task = repository.findActivity(title, user);
                            System.out.print("New content : ");
                            String newContent = new Scanner(System.in).nextLine();
                            task.setContent(newContent);
                            task.setLastUpdate(new Date());
                            task.setDescription("change content of activity with title " + title);
                            repository.saveOrUpdate(task);
                        } else {
                            System.out.println("This title isn't belong to one of your activities");
                            System.out.println("Try again");
                        }
                    } else if (choice == 2) {
                        break;
                    } else {
                        System.out.println("You should choose 1 or 2");
                        System.out.println("Try again");
                    }
                } catch (InputMismatchException exception) {
                    System.out.println("You should enter number");
                    System.out.println("Start over");
                }
            }
        }else {
            System.out.println("You don't have any task yet");
        }
    }

    @Override
    public void changeStatus(User user) {
        List<Task> tasks = repository.findUserActivities(user);
        if(tasks.size() != 0){
            while (true) {
                ApplicationContext.getDemonstrateInformation().demonstrateTaskInfo(tasks);
                try {
                    System.out.println("1.Change status            2.Back to main menu");
                    int choice = new Scanner(System.in).nextInt();
                    if (choice == 1) {
                        System.out.println("Enter title of activity you want to change status : ");
                        String title = new Scanner(System.in).nextLine();
                        if (repository.checkTitle(title, user)) {
                            Task task = repository.findActivity(title, user);
                            String oldStatus = task.getStatus();
                            if (task.getStatus().equals("open")) {
                                System.out.println("1.Completed            2.In progress");
                                while (true) {
                                    int status = new Scanner(System.in).nextInt();
                                    if (status == 1) {
                                        String newStatus = "completed";
                                        task.setStatus(newStatus);
                                        task.setLastUpdate(new Date());
                                        task.setDescription("change status of activity with title " + title + " from " + oldStatus + " to " + newStatus);
                                        repository.saveOrUpdate(task);
                                        break;
                                    } else if (status == 2) {
                                        String newStatus = "In progress";
                                        task.setStatus(newStatus);
                                        task.setLastUpdate(new Date());
                                        task.setDescription("change status of activity with title " + title + " from " + oldStatus + " to " + newStatus);
                                        repository.saveOrUpdate(task);
                                        break;
                                    } else {
                                        System.out.println("You should choose 1 or 2");
                                        System.out.println("Try again");
                                    }
                                }
                            } else {
                                System.out.println("Is this task completed?");
                                System.out.println("1.Yes              2.No");
                                while (true) {
                                    int status = new Scanner(System.in).nextInt();
                                    if (status == 1) {
                                        String newStatus = "completed";
                                        task.setStatus(newStatus);
                                        repository.saveOrUpdate(task);
                                        task.setLastUpdate(new Date());
                                        task.setDescription("change status of activity with title " + title + " from " + oldStatus + " to " + newStatus);
                                        break;
                                    } else {
                                        System.out.println("Status of your activity didn't change");
                                        break;
                                    }
                                }
                            }
                        } else {
                            System.out.println("This title isn't belong to one of your activities");
                            System.out.println("Try again");
                        }
                    } else if (choice == 2) {
                        break;
                    } else {
                        System.out.println("You should choose 1 or 2");
                        System.out.println("Try again");
                    }
                } catch (InputMismatchException exception) {
                    System.out.println("You should enter number");
                    System.out.println("Start over");
                }
            }
        }else{
            System.out.println("You don't have any task yet");
        }
    }

    @Override
    public void seeActivities(User user) {
        List<Task> taskList = repository.findUserActivities(user);
        if(taskList.size() != 0){
            while(true){
                try{
                    ApplicationContext.getDemonstration().seeActivitiesMenu();
                    int seeActivitiesBasedOn;
                    while(true){
                        seeActivitiesBasedOn = new Scanner(System.in).nextInt();
                        if(seeActivitiesBasedOn >=1 && seeActivitiesBasedOn <= 4){
                            break;
                        }else{
                            System.out.println("You should choose between menu options");
                            System.out.println("Try again");
                        }
                    }
                    if(seeActivitiesBasedOn == 4){
                        break;
                    }
                    ApplicationContext.getDemonstration().howSeeActivities();
                    int ascOrDesc;
                    while(true){
                        ascOrDesc = new Scanner(System.in).nextInt();
                        if(ascOrDesc == 1 || ascOrDesc == 2){
                            break;
                        }else{
                            System.out.println("You should choose between menu options");
                            System.out.println("Try again");
                        }
                    }
                    List<Task> tasks = repository.sortActivities(seeActivitiesBasedOn,ascOrDesc,user);
                    ApplicationContext.getDemonstrateInformation().demonstrateTaskInfo(tasks);
                }catch (InputMismatchException exception){
                    System.out.println("You should enter number");
                    System.out.println("Try again");
                }
            }
        }else{
            System.out.println("You don't have any task yet");
        }
    }

    @Override
    public void removeUserTasks(User user) {
        List<Task> tasks = repository.findUserActivities(user);
        for(Task task : tasks){
            delete(task);
        }
    }

    @Override
    public void removeUserTask(User user) {
        List<Task> taskList = repository.findUserActivities(user);
        if(taskList.size() != 0){
            while(true){
                try{
                    ApplicationContext.getDemonstrateInformation().demonstrateTaskInfo(taskList);
                    System.out.println("Do you want to delete a task");
                    System.out.println("1.Yes                   2.No");
                    int choice = new Scanner(System.in).nextInt();
                    if(choice == 1){
                        System.out.print("Enter title of task you want to delete : ");
                        String title = new Scanner(System.in).nextLine();
                        boolean titleIsOk = repository.checkTitle(title,user);
                        if(titleIsOk){
                            System.out.println("Final permission");
                            System.out.println("1.Delete it           2.I regret");
                            int finalChoice = new Scanner(System.in).nextInt();
                            if(finalChoice == 1){
                                Task task = repository.findActivity(title,user);
                                ApplicationContext.getTaskServiceImpl().delete(task);
                                System.out.println("This task deleted");
                                break;
                            }
                        }else{
                            System.out.println("This title doesn't belong to one of your tasks");
                        }
                    }else{
                        System.out.println("Nothing deleted");
                        break;
                    }
                }catch (InputMismatchException exception){
                    System.out.println("You should enter number");
                    System.out.println("Try again");
                }
            }
        }else{
            System.out.println("You don't have any task yet");
        }
    }
}
