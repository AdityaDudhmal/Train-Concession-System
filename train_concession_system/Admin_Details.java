package train_concession_system;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class Admin_Details extends HelperMethods{
    // This is an important step, Security Key of College which is known to Authorized Admins Only.
    // Any one from outside will not be able to get access.
    private final String securityKey = "VJTI@123";

    // Admin LogIN method
    void adminLogin() {
        if (!isValidKey()) {
            System.exit(0);
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Login ID : ");
        String adminId = sc.nextLine();
        System.out.print("Enter Password : ");
        String adminPassword = sc.nextLine();

        System.out.println("Verify You Are Not Robot");

//        HelperMethods helperMethods_obj = new HelperMethods();
//        helperMethods_obj.getCaptcha();
//        helperMethods_obj = null;
        getCaptcha();

        System.out.println("You logged in successfully");
        System.out.println();
    }

    void adminLogout() {
        System.out.println("See you soon! Logged Out!");
        System.exit(0);
    }

    // Method to check if entered key is same as original
    boolean isValidKey() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Verify Whether You Really Are An Admin To This Application");
        System.out.println("Enter the security key");
        String key = sc.nextLine();

        if (Objects.equals(key, securityKey)) {
            System.out.println("Verified! Now proceed for login");
            return true;
        }

        System.out.println("You are not an authorized person, Ending Process");
        return false;
    }

    void startWorking(Connection connection) {

        try {
            System.out.println("Now you will get registration id of first person to apply for concession");
            System.out.println("Now proceeding towards final section");

            String retrieveQuery = "SELECT * FROM form_data";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(retrieveQuery);
            //reg_num, fname, mname, lname, idAddress, aadharNum, aadharAddress, start, end, TicketNum
            String fname = null;
            String mname = null;
            String lname = null;
            String start = null;
            String end = null;
            int regID = 0;

            while (resultSet.next()) {
                regID = resultSet.getInt("reg_num");
                fname = resultSet.getString("fname");
                mname = resultSet.getString("mname");
                lname = resultSet.getString("lname");
                String idAddress = resultSet.getString("idAddress");
                long aadharID = resultSet.getLong("aadharNum");
                String aadharAddress = resultSet.getString("aadharAddress");
                start = resultSet.getString("start");
                end = resultSet.getString("end");
                int ticNum = resultSet.getInt("TicketNum");

                System.out.println("The Details Filled by Applicant in Form are as Follows : ");
                System.out.println("Registration Number : " + regID);
                System.out.println("First Name : " + fname);
                System.out.println("Middle Name : " + mname);
                System.out.println("Last Name : " + lname);
                System.out.println("ID Address : " + idAddress);
                System.out.println("Aadhar Number : " + aadharID);
                System.out.println("Aadhar Address : " + aadharAddress);
                System.out.println("Starting Location : " + start);
                System.out.println("Ending Location : " + end);
                System.out.println("Last Season Ticket Number : " + ticNum);
            }

            System.out.println();
            deleteFromDatabase(connection, regID);
            System.out.println();

            int responce = isValidApplication();

            if (responce == 1) {
                approve_application(fname, mname, lname, start, end);
            }
            if (responce == 2) {
                sendGrievance();
            }

            System.out.println("Do you want to continue your work?");
            System.out.println("1. Continue");
            System.out.println("2. End");
            System.out.println();
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your choice : ");
            int a = sc.nextInt();
            sc.nextLine();

            if (a == 1) {
                startWorking(connection);
            } else {
                adminLogout();
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        }

    }

    void approve_application(String fname, String mname, String lname, String from, String to) {
        System.out.println("Your application for Train Concession has been approved");
        System.out.println("Concession Letter is sent to your mail id");
        System.out.println();
        generateLetter(fname, mname, lname, from, to);
        System.out.println();
        System.out.println("Thank You!");
    }
    void sendGrievance() {
        System.out.println("Grievance has been sent to you!");
    }

    void generateLetter(String fname, String mname, String lname, String from, String to) {
        System.out.println("\t\t\t\t=====================================================================================================");
        System.out.println("\t\t\t\t|\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("\t\t\t\t|\t\t\t\t\t\tVeermata Jijabai Technological Institute, Mumbai\t\t\t\t\t\t\t|");
        System.out.println("\t\t\t\t|\t\t\t\t\tValid for stations -----> Matunga/Vadala/Dadar/King's Circle\t\t\t\t\t|");
        System.out.println("\t\t\t\t|\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("\t\t\t\t|\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("\t\t\t\t|\t\t\t\t\t\t\t\tName: " + fname + " " + mname + " " +  lname + "\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("\t\t\t\t|\t\t\t\t\t\t\t\tFrom: " + from + "," + " To: " + to + "\t\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("\t\t\t\t|\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("\t\t\t\t|\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("\t\t\t\t|\t\t\t\t\tThis is an online generated Letter, acceptable at every station\t\t\t\t\t|");
        System.out.println("\t\t\t\t|\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("\t\t\t\t|\t\t\t\t\t\t\t\t\t\tCollege Stamp\t\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("\t\t\t\t|\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("\t\t\t\t=====================================================================================================");
    }

    int isValidApplication() {
        System.out.println("Chose from below");
        System.out.println("1. Application is valid, approve it");
        System.out.println("2. There are grievances, send grievance");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your choice : ");
        int a = sc.nextInt();
        sc.nextLine();

        if (a > 0 && a < 3) {
            return a;
        }

        System.out.println("Enter Valid Choice");
        return isValidApplication();
    }

    void deleteFromDatabase(Connection connection, int regID) {
        String deleteQuery = "DELETE FROM form_data WHERE reg_num = ?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, regID);

            int rowDeleted = preparedStatement.executeUpdate();
            System.out.println("Number of data entries removed from database are : " + rowDeleted);

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        }
    }

}
