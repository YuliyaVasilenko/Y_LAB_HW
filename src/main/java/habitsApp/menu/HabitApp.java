package habitsApp.menu;

import habitsApp.database.DataBaseManager;
import habitsApp.habit.Habit;
import habitsApp.status.Status;
import habitsApp.users.User;

import java.util.Map;

public class HabitApp {
    public static Map<Integer, User> dataBaseUsers;
    public static Map<Integer, User> dataBaseBlockedUsers;
    public static Map<Integer, Habit> dataBaseHabits;
    public static Map<Integer, Status> dataBaseStatusOfHabits;

    public static void main(String[] args) {
        MenuConsole.sayGreeting();
        new DataBaseManager().getDataBases("db");
        int menu;
        User userNow = null;
        do {
            MenuManager menuManager = new MenuManager();
            menu = menuManager.showAndChooseOperationInMainMenu();
            switch (menu) {
                case 1 -> {
                    userNow = menuManager.doAuthorizationOrRegistration(userNow);
                    menuManager.showReminders(userNow);
                }
                case 2 -> userNow = menuManager.doUpdateUser(userNow);
                case 3 -> userNow = menuManager.doLogOut(userNow);
                case 4 -> userNow = menuManager.doDeleteUser(userNow);
                case 5 -> {
                    int menuInHabits;
                    do {
                        menuInHabits = menuManager.showOperationInHabitsMenu(userNow);
                        switch (menuInHabits) {
                            case 1 -> userNow.createHabit();
                            case 2 -> userNow.updateHabit();
                            case 3 -> userNow.deleteHabit();
                            case 4 -> userNow.showAndSortAllHabits();
                            case 6 -> menu = OperationInTheMenu.EXIT.ordinal() + 1;
                        }
                    } while (menuInHabits != 0 && menuInHabits != OperationInHabitsMenu.RETURN_TO_MENU.ordinal() + 1);
                }
                case 6 -> {
                    int menuInHabitsStatuses;
                    do {
                        menuInHabitsStatuses = menuManager.showOperationInHabitsStatusMenu(userNow);
                        switch (menuInHabitsStatuses) {
                            case 1 -> userNow.setStatus();
                            case 5 -> menu = OperationInTheMenu.EXIT.ordinal() + 1;
                        }
                    } while (menuInHabitsStatuses != 0 && menuInHabitsStatuses != OperationInHabitsMenu.RETURN_TO_MENU.ordinal() + 1);
                }
            }
        } while (menu != OperationInTheMenu.EXIT.ordinal() + 1);
        new DataBaseManager().saveDataBases("db");
        MenuConsole.sayGoodbye();
    }
}
