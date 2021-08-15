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
        System.out.println("|   1.See your activities   |");
        System.out.println("|     2.add an activity     |");
        System.out.println("|    3.change the status    |");
        System.out.println("|  4.change activity title  |");
        System.out.println("| 5.change activity content |");
        System.out.println("|     6.Your profile        |");
        System.out.println("|        7.log out          |");
        System.out.println("|    8.back to main menu    |");
        System.out.println("+---------------------------+");
    }

    public void seeActivitiesMenu(){
        System.out.println("+-----------------------+");
        System.out.println("|    1.Based on Status  |");
        System.out.println("|    2.Based on title   |");
        System.out.println("|    3.Based on Date    |");
        System.out.println("|    4.Back to menu     |");
        System.out.println("+-----------------------+");
    }

    public void howSeeActivities(){
        System.out.println("+---------------------+");
        System.out.println("|     1.Descending    |");
        System.out.println("|     2.Ascending     |");
        System.out.println("+---------------------+");
    }
}
