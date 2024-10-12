package habitsApp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckWriting {
    private static Scanner scanner = new Scanner(System.in);

    public static int isRightOperation(int numberOfMinOperation, int numberOfMaxOperation, int exit) {
        String console = scanner.nextLine();
        boolean isInt = false;
        int choice = 0;
        while (!isInt) {
            try {
                choice = Integer.parseInt(console);
                if (choice == exit) break;
                if (choice < numberOfMinOperation || choice > numberOfMaxOperation) {
                    throw new NumberFormatException();
                }
                isInt = true;
            } catch (NumberFormatException e) {
                System.out.println("Please write a correct digit. To exit press " + exit + ".");
                console = scanner.nextLine();
            }
        }
        return choice;
    }

    public static String checkEmail() {
        System.out.println("Please enter your email:");
        String email = scanner.nextLine();
        Pattern patternEmail = Pattern.compile("(.)+@(.)+\\.(.)+");
        Matcher matcherEmail = patternEmail.matcher(email);
        while (!email.equals("0")) {
            if (matcherEmail.find()) {
                return email;
            } else {
                System.out.println("Incorrect email, please write the email again or press 0 to exit to the menu");
                email = scanner.nextLine();
            }
        }
        return null;
    }

    public static String checkWord(String word) {
        System.out.println("Please enter " + word + " or press 0 to exit to the menu");
        String name = scanner.nextLine();
        while (name.isBlank() && !name.equals("0")) {
            System.out.println("Incorrect " + word + ", please write the name again or press 0 to exit to the menu");
            name = scanner.nextLine();
        }
        if (name.equals("0")) name = null;
        return name;
    }

    public static String checkPassword() {
        String password = "1";
        String confirmPassword;
        while (!password.equals("0")) {
            System.out.println("Write your password or press 0 to exit to the menu");
            password = scanner.nextLine();
            if (!password.equals("0")) {
                System.out.println("Confirm your password or press 0 to exit to the menu");
                confirmPassword = scanner.nextLine();
                if (password.equals(confirmPassword)) {
                    return password;
                } else if (!confirmPassword.equals("0")) {
                    System.out.println("Password has not been confirmed, try again");
                }
            }
        }
        return null;
    }

    public static String checkPassword(String passwordSaved) {
        String password = "1";
        while (!password.equals("0")) {
            System.out.println("Write your password or press 0 to exit to the menu");
            password = scanner.nextLine();
            if (password.equals(passwordSaved)) {
                return password;
            }
            else if (!password.equals("0")) {
                System.out.println("Authenticate failed, wrong password, try again.");
            }
        }
        return null;
    }
}
