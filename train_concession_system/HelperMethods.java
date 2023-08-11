package train_concession_system;

import java.util.Scanner;

public class HelperMethods {

    // getCaptcha() method creates a new captcha which user needs to enter.
    void getCaptcha() {
        Scanner sc = new Scanner(System.in);
        int num = (int) Math.floor(Math.random() * 900.0) + 100;
        StringBuilder st = new StringBuilder();
        int b = 0, c = 0;

        while(num > 0) {
            c = num % 10;
            b = (int) (26 * Math.random()) + 1;
            st.append(c).append(Character.toString(b + 'A'));
            num /= 10;
        }

        System.out.println("Captcha: " + st);
        System.out.print("Enter the above Captcha: ");
        String str = sc.nextLine();
//        boolean bool = isValidCaptcha(str, st.toString());

        if (isValidCaptcha(str, st.toString())) {
            return;
        }

        getCaptcha();
    }

    // Method checks whether captcha entered by user and generated captcha by system are equal are not
    boolean isValidCaptcha(String str, String st) {
        return str.equals(st);
    }
}
