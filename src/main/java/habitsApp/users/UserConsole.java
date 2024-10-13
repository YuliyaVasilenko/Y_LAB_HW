package habitsApp.users;

import habitsApp.menu.CheckWriting;

public class UserConsole {
    public String askEmail() {
        return CheckWriting.checkEmail();
    }
    public boolean doAuthorization(User user) {
        String password = CheckWriting.checkPassword(user.getPassword());
        return password != null;
    }

    public User doRegistration(String email) {
        System.out.println("User has not been found, would you like to register? Press 1 to register, press 0 to return to the menu");
        int choice = CheckWriting.isRightOperation(1, 1, 0);
        User user = null;
        if (choice == 1) {
            String password = CheckWriting.checkPassword();
            String name = CheckWriting.checkWord("your name");
            if (password != null && name != null) {
                user = new User(email, name, password);
            }
        }
        return user;
    }

    public int askFieldUpdate() {
        System.out.println("If you want to change your email press 1, to change your name press 2, " +
                "to change your password press 3, to delete your account press 4, to exit press 0");
        return CheckWriting.isRightOperation(1, 3, 0);
    }

    public String askDataUpdate(int choice) {
        String newData;
        switch (choice) {
            case 1 -> newData = CheckWriting.checkEmail();
            case 2 -> newData = CheckWriting.checkWord("your name");
            case 3 -> newData = CheckWriting.checkPassword();
            default -> newData = null;
        }
        return newData;
    }

    public boolean askDeleteUser() {
        System.out.println("Are you sure you want to delete your account? All information will be lost. " +
                "To permanently delete your account press 1, to cancel press 0");
        int confirmChoice = CheckWriting.isRightOperation(1, 1, 0);
        return confirmChoice == 1;
    }

    public static void sayGreeting(User user) {
        if (user != null) {
            System.out.println("Success! Hello " + user.getName() + "!");
        }
    }

    public static void sayUpdate() {
        System.out.println("Success! The changes have been saved.");
    }

    public static void sayDelete() {
        System.out.println("You have delete your account.");
    }

    public static void sayBlocking() {
        System.out.println("Your account has been blocked. Try logging in late.");
    }


}
