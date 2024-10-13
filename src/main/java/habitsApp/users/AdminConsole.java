package habitsApp.users;

import habitsApp.menu.CheckWriting;

public class AdminConsole {

    public String askEmailUser() {
        System.out.println("Which user will you choose? Write user's email");
        return CheckWriting.checkEmail();
    }

    public static void sayGoodbye() {
        System.out.println("Good job, see you later!");
    }
}
