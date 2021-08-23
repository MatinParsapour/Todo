package util;

public class Demonstration {
    public void mainMenu() {
        System.out.println("+-----------------------+");
        System.out.println("|       1.Sign Up       |");
        System.out.println("|        2.Log In       |");
        System.out.println("|         3.Exit        |");
        System.out.println("+-----------------------+");
    }

    public void choicesMenu() {
        System.out.println("+---------------------------+");
        System.out.println("|     1.your activities     |");
        System.out.println("|     2.add an activity     |");
        System.out.println("|      3.Your profile       |");
        System.out.println("|        4.log out          |");
        System.out.println("|          5.Exit           |");
        System.out.println("+---------------------------+");
    }

    public void seeActivitiesMenu() {
        System.out.println("You want to see your activities based on : ");
        System.out.println("+------------+");
        System.out.println("|  1.Status  |");
        System.out.println("|  2.title   |");
        System.out.println("|  3.Date    |");
        System.out.println("+------------+");
    }

    public void howSeeActivities() {
        System.out.println("+---------------------+");
        System.out.println("|     1.Descending    |");
        System.out.println("|     2.Ascending     |");
        System.out.println("+---------------------+");
    }

    public void activities(){
        System.out.println("+--------------------------+");
        System.out.println("|      1.Change order      |");
        System.out.println("|     2.Change status      |");
        System.out.println("|      3.Change title      |");
        System.out.println("|     4.Change content     |");
        System.out.println("| 5.delete completed tasks |");
        System.out.println("|      6.delete a task     |");
        System.out.println("|    7.back to main menu   |");
        System.out.println("+--------------------------+");
    }
}
