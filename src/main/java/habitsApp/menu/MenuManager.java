package habitsApp.menu;

import habitsApp.habit.HabitManager;
import habitsApp.reminder.ReminderManager;
import habitsApp.users.User;
import habitsApp.users.UserAccountManager;

public class MenuManager {
    public int showAndChooseOperationInMainMenu() {
        OperationInTheMenu.showOperation();
        return CheckWriting.isRightOperation(1,
                OperationInTheMenu.values().length, OperationInTheMenu.EXIT.ordinal() + 1);
    }

    public User doAuthorizationOrRegistration(User user) {
        UserAccountManager userAccountManager = new UserAccountManager();
        if (!UserAccountManager.isAuthorized(user)) {
            user = userAccountManager.doAuthorizationOrRegistration();
            if (UserAccountManager.isAuthorized(user)) {
                user = userAccountManager.doAdmin(user);
            }
        } else {
            user = userAccountManager.doLogOut(user);
        }
        return user;
    }

    public User doLogOut(User user) {
        if (UserAccountManager.isAuthorized(user)) {
            user = new UserAccountManager().doLogOut(user);
        }
        return user;
    }

    public User doUpdateUser(User user) {
        if (UserAccountManager.isAuthorized(user)) {
            user = new UserAccountManager().updateUser(user);
        }
        return user;
    }

    public User doDeleteUser(User user) {
        if (UserAccountManager.isAuthorized(user)) {
            new UserAccountManager().deleteUser(user);
            user = null;
        }
        return user;
    }

    public void showReminders(User user) {
        if (UserAccountManager.isAuthorized(user) && ! new HabitManager().findAllHabits(user.getId()).isEmpty()) {
            ReminderManager reminder = new ReminderManager();
            /*reminder.updateAllStatus(user);
            reminder.printReminder(user);*/
        }
    }

    public int showOperationInHabitsMenu(User user) {
        if (UserAccountManager.isAuthorized(user)) {
            OperationInHabitsMenu.showOperation();
            return CheckWriting.isRightOperation(1,
                    OperationInHabitsMenu.values().length, OperationInHabitsMenu.EXIT.ordinal() + 1);
        }
        return 0;
    }

    public int showOperationInHabitsStatusMenu(User user) {
        if (UserAccountManager.isAuthorized(user)) {
            OperationInHabitsStatusesMenu.showOperation();
            return CheckWriting.isRightOperation(1,
                    OperationInHabitsStatusesMenu.values().length, OperationInHabitsStatusesMenu.EXIT.ordinal() + 1);
        }
        return 0;
    }
}
