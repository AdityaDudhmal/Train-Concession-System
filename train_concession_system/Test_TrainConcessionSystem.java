package train_concession_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Test_TrainConcessionSystem {
    // Method to perform initial login activity
    int getLogin() {
        System.out.println("Enter the correct number according to your login type from below");
        System.out.println("1. Admin Login");
        System.out.println("2. Student Login");
        System.out.println("3. Exit");

        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter Your Choice : ");
        int getNum = sc.nextInt();
        System.out.println();

        if (getNum > 0 && getNum <= 3) {
            return getNum;
        }

        return getLogin();
    }

    // Method processing user activities
    void perform_User_Login(Connection connection) {
        Student_Details student_details_obj = new Student_Details();

        // Store the choice code for user creation (New or Old)
        int getChoice = student_details_obj.whichUser();

        if (getChoice == 1) {
            System.out.println("This is the page to create new account!");
            student_details_obj.createNewAccount(connection);
//            student_details_obj.loginStudent();
//            student_details_obj.apply_For_Concession(connection);
        }
        else if (getChoice == 2) {
            System.out.println("You said you have already created an account");
            student_details_obj.loginStudent();
            student_details_obj.apply_For_Concession(connection);
//            student_details_obj.renewApplication();
        }
        else if (getChoice == 3) {
            System.out.println("You chose to exit the program");
            System.out.println("......Good Bye......");
            System.exit(0);
        }

        System.out.println();
    }

    // Method processing admin activities
    void perform_Admin_Login(Connection connection) {
        Admin_Details admin_details_obj = new Admin_Details();
        admin_details_obj.adminLogin();
        admin_details_obj.startWorking(connection);
    }

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/<add your database name>";
        String username = "<add your username>";
        String password = "<add your password>";

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection done!");

            Test_TrainConcessionSystem test_obj = new Test_TrainConcessionSystem();
            int loginNum = test_obj.getLogin();

            switch (loginNum) {
                case 1 -> {
                    System.out.println("You chose Admin Login");
                    test_obj.perform_Admin_Login(connection);
                }
                case 2 -> {
                    System.out.println("You chose Student Login");
                    System.out.println();
                    test_obj.perform_User_Login(connection);
                }
                case 3 -> {
                    System.out.println("You have chosen Exit option!");
                    System.out.println("......Good Bye......");
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to connect");
            e.printStackTrace();
        }

    }


}











