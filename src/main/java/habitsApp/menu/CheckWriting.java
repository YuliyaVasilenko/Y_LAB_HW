package habitsApp.menu;

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

    public static String checkWord(String wordName) {
        System.out.println("Please enter " + wordName + " or press 0 to exit to the menu");
        String wordUser = scanner.nextLine();
        while (wordUser.isBlank() && !wordUser.equals("0")) {
            System.out.println("Incorrect " + wordName + ", please write the " + wordName + " again or press 0 to exit to the menu");
            wordUser = scanner.nextLine();
        }
        if (wordUser.equals("0")) wordUser = null;
        return wordUser;
    }

    public static String checkEmail() {
        String email = checkWord("email");
        Pattern patternEmail = Pattern.compile("(.)+@(.)+\\.(.)+");
        Matcher matcherEmail;
        while (email != null) {
            matcherEmail = patternEmail.matcher(email);
            if (matcherEmail.find()) {
                return email;
            } else {
                System.out.println("Incorrect email, please write the email again");
                email = checkWord("email");
            }
        }
        return null;
    }

    public static String checkPassword() {
        String password = checkWord("password");
        while (password != null) {
            System.out.println("Confirm your password");
            String confirmPassword = checkWord("password");
            if (password.equals(confirmPassword)) {
                return password;
            } else if (!confirmPassword.equals("0")) {
                System.out.println("Password has not been confirmed, try again");
            }
            password = checkWord("password");
        }
        return null;
    }

    public static String checkPassword(String passwordSaved) {
        String password = checkWord("password");
        while (password != null) {
            if (password.equals(passwordSaved)) {
                return password;
            }
            System.out.println("Authenticate failed, wrong password, try again.");
            password = checkWord("password");
        }
        return null;
    }
}
