package habitsApp.menu;

import habitsApp.reminder.ReminderManager;
import habitsApp.users.User;
import habitsApp.users.UserManager;

import java.util.Map;

public class HabitApp {
    public static Map<Integer, User> dataBaseUsers;
    public static Map<Integer, User> dataBaseBlockedUsers;

    public static void main(String[] args) {
        MenuConsole.sayGreeting();
        MenuManager menuManager = new MenuManager();
        dataBaseUsers = menuManager.getDataBaseSaved("allUsersDB");
        dataBaseBlockedUsers = menuManager.getDataBaseSaved("blockedUsersDB");
        int menu;
        User userNow = null;
        do {
            OperationInTheMenu.showOperation();
            menu = CheckWriting.isRightOperation(1, OperationInTheMenu.values().length, OperationInTheMenu.EXIT.ordinal() + 1);
            switch (menu) {
                case 1 -> {
                    userNow = menuManager.doAuthorizationOrRegistration(userNow);
                    ReminderManager reminder = new ReminderManager();
                    reminder.updateAllStatus(userNow);
                    reminder.printReminder(userNow);
                }
                case 2 -> userNow = menuManager.doUpdateUser(userNow);
                case 3 -> userNow = menuManager.doDeleteUser(userNow);
                case 4 -> {
                    if (UserManager.isAuthorized(userNow)) {
                        int menuInHabits;
                        do {
                            OperationOnHabitsInformation.showOperation();
                            menuInHabits = CheckWriting.isRightOperation(1,
                                    OperationOnHabitsInformation.values().length, OperationOnHabitsInformation.EXIT.ordinal() + 1);
                            switch (menuInHabits) {
                                case 1 -> userNow.createHabit();
                                case 2 -> userNow.updateHabit();
                                case 3 -> userNow.deleteHabit();
                                case 4 -> userNow.showAndSortAllHabits();
                                case 6 -> menu = OperationInTheMenu.EXIT.ordinal() + 1;
                            }
                        } while (menuInHabits != OperationOnHabitsInformation.RETURN_TO_MENU.ordinal() + 1);
                    }
                    else {
                        MenuConsole.sayNotAuthorized();
                    }
                }
                case 5 -> {
                    if (UserManager.isAuthorized(userNow)) {
                        int menuInHabits;
                        do {
                            OperationOnHabitsControl.showOperation();
                            menuInHabits = CheckWriting.isRightOperation(1,
                                    OperationOnHabitsControl.values().length, OperationOnHabitsControl.EXIT.ordinal() + 1);
                            switch (menuInHabits) {
                                case 1 -> userNow.setStatus();
                                case 5 -> menu = OperationInTheMenu.EXIT.ordinal() + 1;
                            }
                        } while (menuInHabits != OperationOnHabitsInformation.RETURN_TO_MENU.ordinal() + 1);
                    }
                    else {
                        MenuConsole.sayNotAuthorized();
                    }
                }
            }
        } while (menu != OperationInTheMenu.EXIT.ordinal() + 1);
        new MenuManager().saveDataBase(dataBaseUsers, "allUsersDB");
        new MenuManager().saveDataBase(dataBaseUsers, "blockedUsersDB");
        MenuConsole.sayGoodbye();
    }
}
