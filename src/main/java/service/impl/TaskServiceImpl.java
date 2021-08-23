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
        Task task = new Task(title, content, new Date(), user, new Date(), description);
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
    }

    @Override
    public void changeContent(User user) {
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
    }

    @Override
    public void changeStatus(User user) {
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
                        makeActivityCompleted(task, title, oldStatus);
                        break;
                    } else if (status == 2) {
                        makeActivityInProgress(task, title, oldStatus);
                        break;
                    } else {
                        System.out.println("You should choose 1 or 2");
                        System.out.println("Try again");
                    }
                }
            } else if (task.getStatus().equals("In progress")) {
                System.out.println("Is this task completed?");
                System.out.println("1.Yes              2.No");
                while (true) {
                    int status = new Scanner(System.in).nextInt();
                    if (status == 1) {
                        makeActivityCompleted(task, title, oldStatus);
                    } else {
                        System.out.println("Status of your activity didn't change");
                    }
                    break;
                }
            } else {
                System.out.println("This activity is completed");
            }
        } else {
            System.out.println("This title isn't belong to one of your activities");
            System.out.println("Try again");
        }
    }

    private void makeActivityInProgress(Task task, String title, String oldStatus) {
        String newStatus = "In progress";
        task.setStatus(newStatus);
        task.setLastUpdate(new Date());
        task.setDescription("change status of activity with title " + title + " from " + oldStatus + " to " + newStatus);
        repository.saveOrUpdate(task);
    }

    private void makeActivityCompleted(Task task, String title, String oldStatus) {
        String newStatus = "completed";
        task.setStatus(newStatus);
        task.setLastUpdate(new Date());
        task.setDescription("change status of activity with title " + title + " from " + oldStatus + " to " + newStatus);
        repository.saveOrUpdate(task);
    }

    @Override
    public List<Task> seeActivities(User user) {
        List<Task> tasks;
        ApplicationContext.getDemonstration().seeActivitiesMenu();
        int seeActivitiesBasedOn = viewCategories();
        ApplicationContext.getDemonstration().howSeeActivities();
        int ascOrDesc = ascOrDesc();
        tasks = repository.sortActivities(seeActivitiesBasedOn, ascOrDesc, user);
        return tasks;
    }

    private int viewCategories() {
        int seeActivitiesBasedOn;
        while (true) {
            try {
                seeActivitiesBasedOn = new Scanner(System.in).nextInt();
                if (seeActivitiesBasedOn >= 1 && seeActivitiesBasedOn <= 4) {
                    break;
                } else {
                    System.out.println("You should choose between menu options");
                    System.out.println("Try again");
                }
            } catch (InputMismatchException exception) {
                System.out.println("You should enter number");
                System.out.println("Try again");
            }
        }
        return seeActivitiesBasedOn;
    }

    private int ascOrDesc() {
        int ascOrDesc;
        while (true) {
            try {
                ascOrDesc = new Scanner(System.in).nextInt();
                if (ascOrDesc == 1 || ascOrDesc == 2) {
                    break;
                } else {
                    System.out.println("You should choose between menu options");
                    System.out.println("Try again");
                }
            } catch (InputMismatchException exception) {
                System.out.println("You should enter number");
                System.out.println("Try again");
            }
        }
        return ascOrDesc;
    }

    @Override
    public void removeUserTasks(User user) {
        List<Task> tasks = repository.findUserActivities(user);
        for (Task task : tasks) {
            delete(task);
        }
    }

    @Override
    public void removeUserTask(User user) {
        while (true) {
            System.out.print("Enter title of task you want to delete : ");
            String title = new Scanner(System.in).nextLine();
            boolean titleIsOk = repository.checkTitle(title, user);
            if (titleIsOk) {
                finalDelete(title, user);
            } else {
                System.out.println("This title doesn't belong to one of your tasks");
            }
            break;
        }
    }

    @Override
    public void removeCompletedTasks(User user) {
        List<Task> taskList = repository.findUserActivities(user);
        System.out.println("Are you sure?");
        System.out.println("1.Yes    2.No");
        int choice = new Scanner(System.in).nextInt();
        if (choice == 1) {
            for (Task task : taskList) {
                if (task.getStatus().equals("completed")) {
                    delete(task);
                }
            }
            System.out.println("Your completed tasks are deleted");
        } else {
            System.out.println("Nothing deleted");
        }
    }

    @Override
    public void userActivities(User user) {
        boolean exit = false;
        while (!exit) {
            List<Task> tasks = seeActivities(user);
            if (tasks != null) {
                while (true) {
                    ApplicationContext.getDemonstrateInformation().demonstrateTaskInfo(tasks);
                    ApplicationContext.getDemonstration().activities();
                    int choice = chooseWhatToDo(user);
                    if (choice == 1) {
                        break;
                    } else if (choice == 5) {
                        exit = true;
                        break;
                    } else if (choice == 7) {
                        exit = true;
                        break;
                    }
                }
            } else {
                exit = true;
            }
        }
    }

    private int chooseWhatToDo(User user) {
        int choice;
        while (true) {
            try {
                choice = new Scanner(System.in).nextInt();
                if (choice == 1) {
                    break;
                } else if (choice == 2) {
                    changeStatus(user);
                    break;
                } else if (choice == 3) {
                    changeTitle(user);
                    break;
                } else if (choice == 4) {
                    changeContent(user);
                    break;
                } else if (choice == 5) {
                    removeCompletedTasks(user);
                    break;
                } else if (choice == 6) {
                    removeUserTask(user);
                    break;
                } else if (choice == 7) {
                    break;
                } else {
                    System.out.println("You should choose between menu options");
                }

            } catch (InputMismatchException exception) {
                System.out.println("You should enter number");
                System.out.println("try again");
            }
        }
        return choice;
    }

    private void finalDelete(String title, User user) {
        System.out.println("Final permission");
        System.out.println("1.Delete it           2.I regret");
        int finalChoice = new Scanner(System.in).nextInt();
        if (finalChoice == 1) {
            Task task = repository.findActivity(title, user);
            ApplicationContext.getTaskServiceImpl().delete(task);
            System.out.println("This task deleted");
        }
    }
}
