package util;

import domain.Task;
import domain.User;

import java.util.List;

public class DemonstrateInformation {
    public void demonstratUserInfo(User user) {
        int firstName = user.getFirstName().length(),
                username = user.getUsername().length(),
                password = user.getPassword().length(),
                birthdate = 10;
        int lastName;
        if (user.getLastName() == null) {
            lastName = 4;
        } else {
            lastName = user.getLastName().length();
        }
        int email;
        if (user.getEmail() == null) {
            email = 4;
        } else {
            email = user.getEmail().length();
        }
        int phoneNumber;
        if (user.getPhoneNumber() == null) {
            phoneNumber = 11;
        } else {
            phoneNumber = user.getPhoneNumber().length();
        }

        int dashLength = firstName + lastName + username + password + birthdate + email + phoneNumber + 57;

        printUserHeader(dashLength, firstName, lastName, username, password, birthdate, email, phoneNumber);

        printUserData(dashLength, firstName, lastName, username, password, birthdate, email, phoneNumber, user);
    }


    private void printUserHeader(int dashLength, int firstName,
                                 int lastName, int username, int password,
                                 int birthdate, int email,
                                 int phoneNumber) {
        System.out.print("+");
        for (int i = 0; i < dashLength; i++) {
            System.out.print("-");
        }
        System.out.println("+");

        System.out.format("| %" + (-(firstName + 10)) + "s", "firstname");
        System.out.format("|%" + (-(lastName + 5)) + "s", "lastname");
        System.out.format("|%" + (-(username + 15)) + "s", "username");
        System.out.format("|%" + (-(password + 5)) + "s", "password");
        System.out.format("|%" + (-(birthdate + 5)) + "s", "birthdate");
        System.out.format("|%" + (-(email + 5)) + "s", "email");
        System.out.format("|%" + (-(phoneNumber + 5)) + "s|", "phonenumber");

        System.out.print("\n+");
        for (int i = 0; i < dashLength; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }

    private void printUserData(int dashLength, int firstName,
                               int lastName, int username, int password,
                               int birthdate, int email,
                               int phoneNumber, User user) {
        System.out.format("| %" + (-(firstName + 10)) + "s", user.getFirstName());
        if (user.getLastName() == null) {
            System.out.format("|%" + (-(lastName + 5)) + "s", "");
        } else {
            System.out.format("|%" + (-(lastName + 5)) + "s", user.getLastName());
        }
        System.out.format("|%" + (-(username + 15)) + "s", user.getUsername());
        System.out.format("|%" + (-(password + 5)) + "s", user.getPassword());
        if (user.getBirthDate() == null) {
            System.out.format("|%" + (-(birthdate + 5)) + "s", "");
        } else {
            System.out.format("|%" + (-(birthdate + 5)) + "s", user.getBirthDate());
        }
        if (user.getEmail() == null) {
            System.out.format("|%" + (-(email + 5)) + "s", "");
        } else {
            System.out.format("|%" + (-(email + 5)) + "s", user.getEmail());
        }
        if (user.getPhoneNumber() == null) {
            System.out.format("|%" + (-(phoneNumber + 5)) + "s|", "");
        } else {
            System.out.format("|%" + (-(phoneNumber + 5)) + "s|", user.getPhoneNumber());
        }

        System.out.print("\n+");
        for (int i = 0; i < dashLength; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }

    public void demonstrateTaskInfo(List<Task> tasks) {
        int statusSize = 11, createDateSize = 26, lastUpdateSize = 26;
        int titleSize = 0;
        int contentSize = 0;
        int descriptionSize = 0;
        for (Task task : tasks) {
            if (task.getTitle().length() > titleSize) {
                titleSize = task.getTitle().length();
            }
        }
        for (Task task : tasks) {
            if (task.getContent().length() > contentSize) {
                contentSize = task.getContent().length();
            }
        }
        for (Task task : tasks) {
            if (task.getDescription().length() > descriptionSize) {
                descriptionSize = task.getDescription().length();
            }
        }
        int dashLength = statusSize + createDateSize + lastUpdateSize + descriptionSize + titleSize + contentSize + 36;

        printTaskHeader(dashLength, titleSize, contentSize, lastUpdateSize, descriptionSize, statusSize, createDateSize);

        printTaskInfo(tasks, titleSize, contentSize, statusSize, lastUpdateSize, descriptionSize, createDateSize, dashLength);
    }


    private void printTaskHeader(int dashLength,
                                 int titleSize, int contentSize,
                                 int lastUpdateSize, int descriptionSize,
                                 int statusSize, int dateSize) {
        System.out.print("+");
        for (int headerTitleDash = 0; headerTitleDash < dashLength; headerTitleDash++) {
            System.out.print("-");
        }
        System.out.println("+");

        System.out.format("| %" + (-(titleSize + 5)) + "s", "title");
        System.out.format("|%" + (-(contentSize + 5)) + "s", "content");
        System.out.format("|%" + (-(statusSize + 5)) + "s", "status");
        System.out.format("|%" + (-(dateSize + 5)) + "s", "create date");
        System.out.format("|%" + (-(lastUpdateSize + 5)) + "s", "last update");
        System.out.format("|%" + (-(descriptionSize + 5)) + "s|\n", "description");

        System.out.print("+");
        for (int footerTitleDash = 0; footerTitleDash < dashLength; footerTitleDash++) {
            System.out.print("-");
        }
        System.out.println("+");
    }

    private void printTaskInfo(List<Task> tasks, int titleSize,
                               int contentSize, int statusSize,
                               int lastUpdateSize, int descriptionSize,
                               int dateSize, int dashLength) {
        for (Task task : tasks) {
            System.out.format("| %" + (-(titleSize + 5)) + "s", task.getTitle());
            System.out.format("|%" + (-(contentSize + 5)) + "s", task.getContent());
            System.out.format("|%" + (-(statusSize + 5)) + "s", task.getStatus());
            System.out.format("|%" + (-(dateSize + 5)) + "s", task.getCreationDate());
            System.out.format("|%" + (-(lastUpdateSize + 5)) + "s", task.getLastUpdate());
            System.out.format("|%" + (-(descriptionSize + 5)) + "s|\n", task.getDescription());
        }
        System.out.print("+");
        for (int footerDash = 0; footerDash < dashLength; footerDash++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
}
