package service.impl;

import base.service.BaseEntityServiceImpl;
import domain.User;
import repository.UserRepository;
import service.UserService;
import util.ApplicationContext;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl extends BaseEntityServiceImpl<User, Long, UserRepository> implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public void logIn() {
        System.out.println("<><><> Log in <><><>");
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("username : ");
                String username = input.next();
                System.out.print("password : ");
                String password = input.next();
                if (repository.checkUser(username, password)) {
                    Long userId = repository.findId(username);
                    System.out.println("<><><> You logged in <><><>");
                    mainMenu(userId);
                    break;
                } else {
                    System.out.println("Your username or password is incorrect");
                    System.out.println("1.Back to main menu        2.Try again");
                    int failedLogInChoice = new Scanner(System.in).nextInt();
                    while (failedLogInChoice < 1 || failedLogInChoice > 2) {
                        System.out.println("You should choose 1 or 2");
                        System.out.println("Try again");
                        failedLogInChoice = new Scanner(System.in).nextInt();
                    }
                    if (failedLogInChoice == 1) {
                        break;
                    }
                }
            } catch (InputMismatchException exception) {
                System.out.println("You should enter number");
                System.out.println("Start over");
            }
        }
    }

    private void mainMenu(Long userId){
        while (true) {
            ApplicationContext.getDemonstration().choicesMenu();
            int choice = new Scanner(System.in).nextInt();
            if (choice == 1) {
                ApplicationContext.getTaskServiceImpl().seeActivities(findById(userId));
            } else if (choice == 2) {
                ApplicationContext.getTaskServiceImpl().addActivity(findById(userId));
            } else if (choice == 3) {
                ApplicationContext.getTaskServiceImpl().changeStatus(findById(userId));
            } else if (choice == 4) {
                ApplicationContext.getTaskServiceImpl().changeTitle(findById(userId));
            } else if (choice == 5) {
                ApplicationContext.getTaskServiceImpl().changeContent(findById(userId));
            } else if (choice == 6) {
                ApplicationContext.getTaskServiceImpl().removeUserTask(findById(userId));
            } else if (choice == 7) {
                profile(userId);
            } else if (choice == 8) {
                logOut(findById(userId));
                break;
            } else if (choice == 9) {
                break;
            }
        }
    }

    @Override
    public void signUp() {
        System.out.println("<><><> Sign up <><><>");
        String name = name();
        String username = username();
        String password = password();
        User user = new User(name, username, password);
        ApplicationContext.getUserServiceImpl().repository.saveOrUpdate(user);
        logIn();
    }

    private String name() {
        String name;
        while (true) {
            try {
                System.out.print("name : ");
                name = new Scanner(System.in).next();
                System.out.println("1.Acceptable            2.Unacceptable");
                int setName = new Scanner(System.in).nextInt();
                if (setName == 1) {
                    break;
                } else {
                    System.out.println("Now try again");
                }
            } catch (InputMismatchException exception) {
                System.out.println("You have to enter number");
                System.out.println("Start over");
            }
        }
        return name;
    }

    private String username() {
        String username;
        while (true) {
            try {
                System.out.print("Username : ");
                username = new Scanner(System.in).next();
                if (!ApplicationContext.getUserServiceImpl().repository.exists(username)) {
                    System.out.println("1.Acceptable              2.Unacceptable");
                    int choice = new Scanner(System.in).nextInt();
                    if (choice == 1) {
                        break;
                    } else {
                        System.out.println("Now try again");
                    }
                } else {
                    System.out.println("Sorry!! there is a username like this");
                    System.out.println("try another username");
                }
            } catch (InputMismatchException exception) {
                System.out.println("You should enter number");
                System.out.println("Start over");
            }
        }
        return username;
    }

    private String password() {
        String password;
        while (true) {
            try {
                Pattern pattern = Pattern.compile("[0-9]{10}");
                System.out.print("Password : ");
                password = new Scanner(System.in).next();
                Matcher matcher = pattern.matcher(password);
                while (!matcher.matches()) {
                    System.out.println("You should enter a 10-digit password");
                    System.out.println("Try again");
                    password = new Scanner(System.in).next();
                    matcher = pattern.matcher(password);
                }
                System.out.println("1.Acceptable         2.Unacceptable");
                int choice = new Scanner(System.in).nextInt();
                if (choice == 1) {
                    break;
                } else {
                    System.out.println("Nowtry again");
                }
            } catch (InputMismatchException exception) {
                System.out.println("You should enter number");
                System.out.println("Start over");
            }
        }
        return password;
    }

    @Override
    public void profile(Long id) {
        User user = findById(id);
        while (true) {
            try {
                ApplicationContext.getDemonstrateInformation().demonstratUserInfo(user);
                System.out.println("1.Change profile         2.Delete a field         3.Back to main menu");
                int choice = new Scanner(System.in).nextInt();
                if (choice == 3) {
                    break;
                } else if (choice == 1) {
                    changeProfileFields(user);
                } else if (choice == 2) {
                    deleteProfileFields(user);
                } else {
                    System.out.println("You should choose 1 or 2");
                    System.out.println("Try again");
                }
            } catch (InputMismatchException | InterruptedException exception) {
                System.out.println("You should enter number");
                System.out.println("Try again");
            }
        }
    }

    @Override
    public void logOut(User user) {
        System.out.println("Are you sure");
        System.out.println("1.Yes   2.No");
        int finalChoice = new Scanner(System.in).nextInt();
        if (finalChoice == 1) {
            ApplicationContext.getTaskServiceImpl().removeUserTasks(user);
            ApplicationContext.getUserServiceImpl().delete(user);
            System.out.println("We already missed you");
            System.out.println("Have a good day");
        } else {
            System.out.println("You are still logged in");
        }
    }


    private LocalDate birthDate() {
        LocalDate date;
        while (true) {
            try {
                System.out.print("Year : ");
                int year = new Scanner(System.in).nextInt();
                System.out.print("Month : ");
                int month = new Scanner(System.in).nextInt();
                while (month < 1 || month > 12) {
                    System.out.println("This is not a valid number for month");
                    System.out.println("Try again");
                    month = new Scanner(System.in).nextInt();
                }
                System.out.print("Day : ");
                int day = new Scanner(System.in).nextInt();
                while (day < 1 || day > 31) {
                    System.out.println("This is not a valid number for day");
                    System.out.println("Try again");
                    day = new Scanner(System.in).nextInt();
                }
                date = LocalDate.of(year, month, day);
                if (LocalDate.now().isAfter(date)) {
                    break;
                } else {
                    System.out.println("This is not a valid date for your birthday");
                    System.out.println("Try again");
                }
            } catch (InputMismatchException exception) {
                System.out.println("You should enter number");
                System.out.println("Try again");
            }
        }
        return date;
    }

    private String email(String previousEmail) throws InterruptedException {
        Random random = new Random();
        Pattern validEmail = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[\\a-zA-Z]{2,6}");
        System.out.print("Email : ");
        String email = new Scanner(System.in).next();
        Matcher machEmail = validEmail.matcher(email);
        while (!machEmail.matches()) {
            System.out.println("this is not a valid email");
            System.out.println("Try again");
            email = new Scanner(System.in).next();
            machEmail = validEmail.matcher(email);
        }
        while (true) {
            int validationCode = random.nextInt(1000000);
            System.out.println("Please wait, we are sending a validation code to " + email);
            for (int waiting = 0; waiting <= 10; waiting++) {
                Thread.sleep(1000);
                System.out.print("" + "🟩");
            }
            System.out.println();
            System.out.println("This is your validation code : " + validationCode);
            System.out.print("Write your validation code : ");
            int validate = new Scanner(System.in).nextInt();
            if (validate == validationCode) {
                System.out.print("Please wait, we are syncing data");
                for (int delay = 0; delay <= 10; delay++) {
                    Thread.sleep(1000);
                    System.out.print(" .");
                }
                System.out.println("Now you are good to go");
                System.out.println("\nYour email is valid");
                return email;
            } else {
                System.out.println("Invalid code");
                System.out.println("1.Send another code       2.back to main menu");
                int choice = new Scanner(System.in).nextInt();
                if (choice == 2) {
                    return previousEmail;
                }
            }
        }
    }

    private String phoneNumber(String number) throws InterruptedException {
        Random random = new Random();
        Pattern validPhoneNumber = Pattern.compile("[0][9][0-9]{9}");
        System.out.println("Enter your full phone number");
        String phoneNumber = new Scanner(System.in).next();
        Matcher matchPhoneNumber = validPhoneNumber.matcher(phoneNumber);
        while (!matchPhoneNumber.matches()) {
            System.out.println("This is not a valid phone number");
            System.out.println("Try again");
            phoneNumber = new Scanner(System.in).next();
            matchPhoneNumber = validPhoneNumber.matcher(phoneNumber);
        }
        while (true) {
            int validationCode = random.nextInt(1000000);
            System.out.println("Please wait, we are sending a validation code to " + phoneNumber);
            for (int waiting = 0; waiting <= 10; waiting++) {
                Thread.sleep(1000);
                System.out.print("" + "🟩");
            }
            System.out.println("\nThis is your validation code : " + validationCode);
            System.out.print("Write your validation code : ");
            int validate = new Scanner(System.in).nextInt();
            if (validate == validationCode) {
                System.out.println("Your phone is valid");
                return phoneNumber;
            } else {
                System.out.println("Invalid code");
                System.out.println("1.Send another code       2.back to main menu");
                int choice = new Scanner(System.in).nextInt();
                if (choice == 2) {
                    return number;
                }
            }
        }
    }

    private void changeProfileFields(User user) throws InterruptedException {
        System.out.println("Which one?");
        String changeProfile = new Scanner(System.in).nextLine();
        switch (changeProfile.toLowerCase()) {
            case "name":
                String name = name();
                user.setFirstName(name);
                repository.saveOrUpdate(user);
                System.out.println("!!! Your name changed !!!");
                break;
            case "username":
                String username = username();
                user.setUsername(username);
                repository.saveOrUpdate(user);
                System.out.println("!!! Your username changed !!!");
                break;
            case "password":
                String password = password();
                user.setPassword(password);
                repository.saveOrUpdate(user);
                System.out.println("!!! Your password changed !!!");
                break;
            case "birthdate":
                LocalDate birthDate = birthDate();
                user.setBirthDate(birthDate);
                repository.saveOrUpdate(user);
                System.out.println("!!! Your birth Date changed !!!");
                break;
            case "email":
                String email = email(user.getEmail());
                user.setEmail(email);
                repository.saveOrUpdate(user);
                System.out.println("!!! Your email changed !!!");
                break;
            case "phonenumber":
                String phoneNumber = phoneNumber(user.getPhoneNumber());
                user.setPhoneNumber(phoneNumber);
                repository.saveOrUpdate(user);
                System.out.println("!!! Your phone number changed !!!");
                break;
            default:
                System.out.println("You should enter name of field like the what you see");
        }
    }

    private void deleteProfileFields(User user) {
        System.out.println("Which one?");
        String deleteProfile = new Scanner(System.in).next();
        try {
            switch (deleteProfile.toLowerCase()) {
                case "name":
                case "username":
                case "password":
                    System.out.println("You can't delete this field");
                    break;
                case "birthdate":
                    user.getBirthDate().equals(null);
                    System.out.println("Are you sure?");
                    System.out.println("1.Yes    2.No");
                    int choice = new Scanner(System.in).nextInt();
                    if (choice == 1) {
                        user.setBirthDate(null);
                        repository.saveOrUpdate(user);
                        System.out.println("Your birthdate is clear");
                        break;
                    } else {
                        System.out.println("Nothing changed");
                        break;
                    }
                case "email":
                    user.getEmail().equals(null);
                    System.out.println("Are you sure?");
                    System.out.println("1.Yes    2.No");
                    choice = new Scanner(System.in).nextInt();
                    if (choice == 1) {
                        user.setEmail(null);
                        repository.saveOrUpdate(user);
                        System.out.println("Your email is clear");
                        break;
                    } else {
                        System.out.println("Nothing changed");
                        break;
                    }
                case "phonenumber":
                    user.getPhoneNumber().equals(null);
                    System.out.println("Are you sure?");
                    System.out.println("1.Yes    2.No");
                    choice = new Scanner(System.in).nextInt();
                    if (choice == 1) {
                        user.setPhoneNumber(null);
                        repository.saveOrUpdate(user);
                        System.out.println("Your phone number is clear");
                        break;
                    } else {
                        System.out.println("Nothing changed");
                        break;
                    }
            }
        } catch (NullPointerException exception) {
            System.out.println("This field already has nothing");
        }
    }
}
