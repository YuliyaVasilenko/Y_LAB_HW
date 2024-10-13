package habitsApp.menu;

public class MenuConsole {
    public static void sayGreeting() {
        System.out.println("Welcome to our habit control application! Please log in.");
    }

    public static void sayAlreadyAuthorized() {
        System.out.println("You are already logged in. To log out press 1 or press 0 to return to menu");
    }

    public static void sayNotAuthorized() {
        System.out.println("You are not logged in. Log in or registry first.");
    }

    public static void sayLogOut() {
        System.out.println("You are log out.");
    }


    public static void sayGoodbye() {
        System.out.println("Thanks for visiting!\nSee you soon!");
    }
}
