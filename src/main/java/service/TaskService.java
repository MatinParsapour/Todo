package service;

import entity.Tasks;
import entity.User;
import repository.TaskRepository;


import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addActivity(User user) {
        System.out.println("<><><> add an activity <><><>");
        String title = title();
        String body = body();
        LocalDate creationDate = LocalDate.now();
        Tasks task = new Tasks(title, body, creationDate, user);
        taskRepository.addActivity(task);
    }

    private String title() {
        String title;
        while (true) {
            try {
                System.out.println("   Title   ");
                title = new Scanner(System.in).next();
                System.out.println("1.Acceptable     2.Unacceptable");
                int choice = new Scanner(System.in).nextInt();
                if (choice == 1)
                    break;
                else if (choice == 2) {
                    System.out.println("Now enter another title");
                } else {
                    System.out.println("you should choose 1 or 2");
                }
            } catch (InputMismatchException exception) {
                System.out.println("You should enter number");
                System.out.println("Start over");
            }
        }
        return title;
    }

    private String body() {
        String body;
        while (true) {
            try {
                System.out.println("    Task   ");
                body = new Scanner(System.in).nextLine();
                System.out.println("1.Acceptable         2.Unacceptable");
                int choice = new Scanner(System.in).nextInt();
                if (choice == 1) {
                    break;
                } else if (choice == 2) {
                    System.out.println("Now edit your task content");
                } else {
                    System.out.println("You should choose 1 or 2");
                }
            } catch (InputMismatchException exception) {
                System.out.println("You should enter number");
                System.out.println("Try again");
            }
        }
        return body;
    }

    public void changeStatus(User user) {
        List<Tasks> tasks = taskRepository.findUserActivities(user);
        for (int counter = 0; counter < tasks.size(); counter++) {
            System.out.println("Title : " + tasks.get(counter).getTitle()
                    + " Content : " + tasks.get(counter).getBody()
                    + " Status : " + tasks.get(counter).getStatus());
        }
        while (true) {
            try {
                System.out.println("Do you want to change status of a task?");
                System.out.println("1.Yes                              2.No");
                int choice = new Scanner(System.in).nextInt();
                if (choice == 1) {
                    System.out.println("Enter title of task");
                    String taskTitle = new Scanner(System.in).next();
                    boolean isIdValid = taskRepository.isIdCorrect(taskTitle, user);
                    if (!isIdValid) {
                        System.out.println("The title you entered is not one of your tasks");
                        System.out.println("1.back to menu                  2.try again");
                        int invalidId = new Scanner(System.in).nextInt();
                        if (invalidId == 2) {
                            System.out.println("start over");
                        } else if (invalidId == 1) {
                            break;
                        } else {
                            System.out.println("You should choose 1 or 2");
                            System.out.println("try again");
                        }
                    } else {
                        for (int findTask = 0; findTask < tasks.size(); findTask++) {
                            if (tasks.get(findTask).getTitle().equals(taskTitle) && tasks.get(findTask).getStatus().equals("open")) {
                                int id = tasks.get(findTask).getId();
                                while (true) {
                                    System.out.println("1.completed            2.in progress");
                                    int status = new Scanner(System.in).nextInt();
                                    if (status == 1 || status == 2) {
                                        taskRepository.changeStatus(id, status);
                                        break;
                                    } else {
                                        System.out.println("You should choose 1 or 2");
                                        System.out.println("try again");
                                    }
                                }
                                break;
                            } else if (tasks.get(findTask).getTitle().equals(taskTitle) && tasks.get(findTask).getStatus().equals("in progress")) {
                                int id = tasks.get(findTask).getId();
                                System.out.println("Is this task completed?");
                                System.out.println("1.Yes              2.NO");
                                int status = new Scanner(System.in).nextInt();
                                if (status == 1) {
                                    taskRepository.changeStatus(id, status);
                                } else {
                                    System.out.println("Nothing changed");
                                }
                                break;
                            }

                        }
                    }
                } else if (choice == 2) {
                    break;
                } else {
                    System.out.println("You should choose 1 or 2");
                    System.out.println("try again");
                }
            } catch (InputMismatchException exception) {
                System.out.println("You should enter number");
                System.out.println("start over");
            }
        }
    }
}
