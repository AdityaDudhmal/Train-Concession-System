package train_concession_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Student_Details extends HelperMethods {
    // User Variables
    private String fName;
    private String mName;
    private String lName;
    private int regNo;
    private String programme;
    private long contactNo;
    private String idAddress;
    private long aadhaarNo;
    private String aadhaarAddress;
    private String loginId;
    private String password;

    public Student_Details() {
        this.fName = null;
        this.mName = null;
        this.lName = null;
        this.regNo = 0;
        this.programme = null;
        this.contactNo = 0;
        this.idAddress = null;
        this.aadhaarNo = 0;
        this.aadhaarAddress = null;
        this.loginId = null;
        this.password = null;
    }

    // Method to create new account
    void createNewAccount(Connection connection) {
        try {
            Scanner sc = new Scanner(System.in);

            String insertQuery = "INSERT INTO STUDENT_DATA (fname, mname, lname, reg_num, programme, contact, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            System.out.println("Fill up the form given below");

            System.out.print("Enter Your First Name : ");
            fName = sc.nextLine();
            System.out.print("Enter Your Middle Name : ");
            mName = sc.nextLine();
            System.out.print("Enter Your Last Name : ");
            lName = sc.nextLine();
            System.out.print("Enter College Registration Number : ");
            regNo = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Your Programme (Diploma/B.Tech/M.Tech/PhD) : ");
            programme = sc.nextLine();
            System.out.print("Enter Your Contact Number : ");
            contactNo = sc.nextLong();
            sc.nextLine();
            System.out.print("Enter Your Address as per Identity Card (Only name of CITY) : ");
            idAddress = sc.nextLine();
            System.out.println();

            preparedStatement.setString(1, fName);
            preparedStatement.setString(2, mName);
            preparedStatement.setString(3, lName);
            preparedStatement.setInt(4, regNo);
            preparedStatement.setString(5, programme);
            preparedStatement.setLong(6, contactNo);
            preparedStatement.setString(7, idAddress);

            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println(rowsAffected + " row(s) inserted.");

            connection.commit();

            System.out.println("Now Enter Your Login Id and Password");
            System.out.print("Enter Login Id (Kindly keep your college registration number as login Id ) : ");
            loginId = sc.nextLine();
            System.out.print("Enter Password : ");
            password = sc.nextLine();

            System.out.println();
            System.out.println("You Have Successfully Created Your Account");
            System.out.println();

            loginStudent();
            apply_For_Concession(connection);

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Something Went Wrong!");
            e.printStackTrace();
        }

    }

    // LogIn method for User (Student)
    void loginStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Now let's head towards login");
        System.out.print("Enter Username : ");
        String loginIDValue = sc.nextLine();
        System.out.print("Enter PassWord : ");
        String loginPassword = sc.nextLine();

//        if (!Objects.equals(loginIDValue, loginId) || !Objects.equals(loginPassword, password)) {
//            System.out.println("Incorrect ID or Password! Try Again");
//            loginStudent();
//        }

        System.out.println("Verify you are not robot...");
        getCaptcha();

        System.out.println("You Logged In Successfully!");
        System.out.println();
    }

    // LogOut method for User (Student)
    void logoutStudent() {
        System.out.println("See you soon! Logged Out!");
        System.exit(0);
    }

    // Students can apply for concession using this method
    void apply_For_Concession(Connection connection) {
        Scanner sc = new Scanner(System.in);

        try {
            String insertQuery = "INSERT INTO form_data (reg_num, fname, mname, lname, idAddress, aadharNum, aadharAddress, start, end, TicketNum) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            System.out.println("Now Kindly Apply for Concession");

            System.out.print("Enter College Registration Number : ");
            regNo = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Your First Name : ");
            fName = sc.nextLine();
            System.out.print("Enter Your Middle Name : ");
            mName = sc.nextLine();
            System.out.print("Enter Your Last Name : ");
            lName = sc.nextLine();
            System.out.print("Enter Your Address as per Identity Card (Only name of CITY) : ");
            idAddress = sc.nextLine();

            System.out.println("Moving towards Aadhaar Card Information");

            System.out.print("Enter Your Aadhaar Number : ");
            aadhaarNo = sc.nextLong();
            sc.nextLine();
            System.out.print("Enter Your Address as per Aadhaar Card : ");
            aadhaarAddress = sc.nextLine();
            System.out.println();

            if (!Objects.equals(aadhaarAddress, idAddress)) {
                System.out.println("Sorry! Your address does not match, can not process this application!");
                System.out.println("To prevent any system clashes, we are logging you out");
                logoutStudent();
            }

            // Starting location will be home location
            String startingLocation = idAddress;
            System.out.println("Enter Ending Location of Pass You Want (Matunga/Vadala/Dadar/King's Circle) : ");
            String endLocation = sc.nextLine();

            System.out.println("If you have previous pass then enter the 4 digit season ticket number on it (else enter 1): ");
            int seasonTicNum = sc.nextInt();
            sc.nextLine();

            preparedStatement.setInt(1, regNo);
            preparedStatement.setString(2, fName);
            preparedStatement.setString(3, mName);
            preparedStatement.setString(4, lName);
            preparedStatement.setString(5, idAddress);
            preparedStatement.setLong(6, aadhaarNo);
            preparedStatement.setString(7, aadhaarAddress);
            preparedStatement.setString(8, startingLocation);
            preparedStatement.setString(9, endLocation);
            preparedStatement.setInt(10, seasonTicNum);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");

            connection.commit();

            preparedStatement.close();

            System.out.println("The form has ended...Now submitting the form");
            submitApplication(regNo);

            connection.close();

        } catch (SQLException e) {
            System.out.println("Something Went Wrong!");
            e.printStackTrace();
        }

    }

//    void renewApplication() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("This is the start of application");
//        System.out.print("Enter Your Address as per Identity Card (Only name of CITY) : ");
//        idAddress = sc.nextLine();
//        apply_For_Concession_For_First_Time();
//
//        System.out.println("Enter Previous Pass Details");
//        System.out.println();
//
//        System.out.println("Enter Starting Location of Pass");
//        String from = sc.nextLine();
//        System.out.println("Enter Ending Location of Pass");
//        String to = sc.nextLine();
//        System.out.println("Enter Season Ticket Number on Your Pass (Four Digit Number)");
//        int seasonTicNum = sc.nextInt();
//        sc.nextLine();
//
//        System.out.println();
//
//    }

    // Ask User if he/she has previous account or not
    int whichUser() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose appropriate option from given below");
        System.out.println("1. Create New Account");
        System.out.println("2. Already Have an Account");
        System.out.println("3. Exit From Application");
        System.out.println();

        int getChoice = sc.nextInt();
        System.out.println();

        if (getChoice > 0 && getChoice <= 3) {
            return getChoice;
        }

        return whichUser();
    }

    // Submit the application after filling the form
    void submitApplication(int registration_number) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to submit your form? Choose from below");
        System.out.println("1. Submit Form");
        System.out.println("2. Quit Process");

        System.out.println("Enter your choice");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            System.out.println("You have successfully submitted your application");

            // Add user application to Queue (Form checking will occur using FCFS principle)
            FCFS_Application fcfs_application_obj = new FCFS_Application();
            fcfs_application_obj.add_application_to_queue(registration_number);
            System.out.println("Your application has been added to queue with registration number " + registration_number);
            logoutStudent();

        }
        if (choice == 2) {
            System.out.println("You chose to leave the process but your form will be auto submitted");

        }
        else {
            System.out.println("Enter valid choice code");
            submitApplication(registration_number);
        }
        logoutStudent();
    }

}
