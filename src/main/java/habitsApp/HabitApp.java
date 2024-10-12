package habitsApp;

import habitsApp.users.Admin;
import habitsApp.users.AllUsersDB;
import habitsApp.users.User;
import habitsApp.users.habits.Habit;
import habitsApp.users.habits.OperationOnHabitsControl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class HabitApp {
    public static Map<String, User> dataBaseUsers;
    public static List<Habit> dataBaseHabits;
    public static Map<LocalDate, Boolean> dataBaseStatus;

    public static void main(String[] args) {
        System.out.println("Welcome to our habit registration application!");
        int menu;
        User userNow = null;
        AllUsersDB dataBaseSaved = AllUsersDB.deserialization();
        if (dataBaseSaved == null) {
            dataBaseSaved = new AllUsersDB();
        }
        dataBaseUsers = dataBaseSaved.getdb();
        do {
            OperationInTheMenu.showOperation();
            menu = CheckWriting.isRightOperation(1, OperationInTheMenu.values().length, OperationInTheMenu.EXIT.ordinal() + 1);
            switch (menu) {
                case 1 -> {
                    if (!User.isAuthorized(userNow)) {
                        userNow = User.doAuthorizationOrRegistration();
                        dataBaseHabits = userNow.getHabits().getList();
                        if (userNow.getEmail().equals("admin@mail.com")) {
                            Admin admin = new Admin(userNow);
                            int choiceAdmin;
                            do {
                                System.out.println("What job you want to do: press 1 to show all user, " +
                                        "press 2 to delete user or press 0 to exit");
                                choiceAdmin = CheckWriting.isRightOperation(1, 2, 0);
                                switch (choiceAdmin) {
                                    case 1 -> admin.findAllUsers();
                                    case 2 -> admin.deleteUser();
                                }
                            } while (choiceAdmin != 0);
                        }
                    } else {
                        System.out.println("You are already logged in. To log out press 1 or press 0 to return to menu");
                        int choiceLogOut = CheckWriting.isRightOperation(1, 1, 0);
                        if (choiceLogOut == 1) {
                            userNow = null;
                            System.out.println("You are log out.");
                        }
                    }
                }
                case 2 -> { if (User.isAuthorized(userNow)) userNow.changeData(); }
                case 3 -> { if (User.isAuthorized(userNow)) userNow.deleteUser(); }
                case 4 -> {
                    if (User.isAuthorized(userNow)) {
                        int menuInHabits;
                        do {
                            OperationOnHabitsInformation.showOperation();
                            menuInHabits = CheckWriting.isRightOperation(1,
                                    OperationOnHabitsInformation.values().length, OperationOnHabitsInformation.RETURN_TO_MENU.ordinal() + 1);
                            if (menuInHabits == OperationOnHabitsInformation.EXIT.ordinal() + 1) {
                                menu = OperationInTheMenu.EXIT.ordinal() + 1;
                                break;
                            }
                            switch (menuInHabits) {
                                case 1 -> userNow.createHabit();
                                case 2 -> userNow.updateHabit();
                                case 3 -> userNow.deleteHabit();
                                case 4 -> userNow.showAndSortAllHabits();
                            }
                        } while (menuInHabits != 0 && menuInHabits != OperationOnHabitsInformation.RETURN_TO_MENU.ordinal() + 1);
                    }
                }
                case 5 -> {
                    if (User.isAuthorized(userNow)) {
                        int menuInHabits;
                        do {
                            OperationOnHabitsControl.showOperation();
                            menuInHabits = CheckWriting.isRightOperation(1,
                                    OperationOnHabitsControl.values().length, OperationOnHabitsControl.RETURN_TO_MENU.ordinal() + 1);
                            if (menuInHabits == OperationOnHabitsControl.EXIT.ordinal() + 1) {
                                menu = OperationInTheMenu.EXIT.ordinal() + 1;
                                break;
                            }
                            switch (menuInHabits) {
                                case 1 -> userNow.setStatus();
                            }
                        } while (menuInHabits != 0 && menuInHabits != OperationOnHabitsInformation.RETURN_TO_MENU.ordinal() + 1);
                    }
                }
            }
        } while (menu != OperationInTheMenu.EXIT.ordinal() + 1);
        dataBaseSaved.setDb(dataBaseUsers);
        dataBaseSaved.serialization();
        System.out.println("Thanks for visiting!\nSee you soon!");
    }
}
