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
        Task task = new Task(title, content, new Date(), user);
        repository.saveOrUpdate(task);
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
        while (true) {
            List<Task> tasks = repository.findUserActivities(user);
            for (Task task : tasks) {
                System.out.println("Title : " + task.getTitle());
                System.out.println("Content : " + task.getContent());
                System.out.println("Status : " + task.getStatus());
            }
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
    }

    @Override
    public void changeContent(User user) {
        while (true) {
            List<Task> tasks = repository.findUserActivities(user);
            for (Task task : tasks) {
                System.out.println("Title : " + task.getTitle());
                System.out.println("Content : " + task.getContent());
                System.out.println("Status : " + task.getStatus());
            }
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
    }

    @Override
    public void changeStatus(User user) {
        while (true) {
            List<Task> tasks = repository.findUserActivities(user);
            for (Task task : tasks) {
                System.out.println("Title : " + task.getTitle());
                System.out.println("Content : " + task.getContent());
                System.out.println("Status : " + task.getStatus());
            }
            try {
                System.out.println("1.Change status            2.Back to main menu");
                int choice = new Scanner(System.in).nextInt();
                if (choice == 1) {
                    System.out.println("Enter title of activity you want to change status : ");
                    String title = new Scanner(System.in).nextLine();
                    if (repository.checkTitle(title, user)) {
                        Task task = repository.findActivity(title, user);
                        if (task.getStatus().equals("open")) {
                            System.out.println("1.Completed            2.In progress");
                            while (true) {
                                int status = new Scanner(System.in).nextInt();
                                if (status == 1) {
                                    task.setStatus("completed");
                                    repository.saveOrUpdate(task);
                                    break;
                                } else if (status == 2) {
                                    task.setStatus("In progress");
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
                                    task.setStatus("completed");
                                    repository.saveOrUpdate(task);
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
    }

    @Override
    public void seeActivities(User user) {
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
                for(Task task : tasks){
                    System.out.println("********************************");
                    System.out.printf("Title : %-10s\n" , task.getTitle());
                    System.out.printf("Content : %-10s\n" , task.getContent());
                    System.out.printf("Date : %-10s\n" , task.getCreationDate());
                    System.out.printf("Status : %-10s\n" , task.getStatus());
                    System.out.println("********************************");
                }
            }catch (InputMismatchException exception){
                System.out.println("You should enter number");
                System.out.println("Try again");
            }
        }
    }

    @Override
    public void removeUserTasks(User user) {
        List<Task> tasks = repository.findUserActivities(user);
        for(Task task : tasks){
            delete(task);
        }
    }
}
