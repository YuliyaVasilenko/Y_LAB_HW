package habitsApp.users;

import habitsApp.dao.UserDAO;
import habitsApp.habit.Habit;
import habitsApp.habit.HabitManager;
import habitsApp.menu.CheckWriting;
import habitsApp.menu.MenuAdmin;
import habitsApp.menu.MenuConsole;
import habitsApp.status.Status;
import habitsApp.status.StatusManager;

public class UserAccountManager {
    private int cancelOperation = 0;

    public User doAuthorizationOrRegistration() {
        String email = new UserAccountManagerConsole().askEmail();
        if (email == null) {
            return null;
        }
        User user = new UserDAO().find(email);
        if (user != null) {
            if (!doAuthorization(user)) {
                return null;
            }
        } else {
            user = doRegistration(email);
        }
        if (user != null) {
            UserAccountManagerConsole.sayGreeting(user);
        }
        return user;
    }

    public boolean doAuthorization(User user) {
        if (!new UserAccountManagerConsole().doAuthorization(user)) {
            return false;
        } else if (new UserDAO().checkBlocking(user.getId())) {
            UserAccountManagerConsole.sayBlocking();
            return false;
        }
        return true;
    }

    public User doRegistration(String email) {
        User user = new UserAccountManagerConsole().doRegistration(email);
        if (user != null) {
            UserDAO userDAO = new UserDAO();
            if (userDAO.create(user)) {
                user = userDAO.find(email);
            }
        }
        return user;
    }

    public User updateUser(User user) {
        int fieldUpdate = new UserAccountManagerConsole().askFieldUpdate();
        if (fieldUpdate != cancelOperation) {
            String newData = new UserAccountManagerConsole().askDataUpdate(fieldUpdate);
            if (newData != null) {
                switch (fieldUpdate) {
                    case 1 -> user.setEmail(newData);
                    case 2 -> user.setName(newData);
                    case 3 -> user.setPassword(newData);
                }
                user = new UserDAO().update(user);
                UserAccountManagerConsole.sayUpdate();
            }
        }
        return user;
    }

    public boolean deleteUser(User user) {
        if (new UserAccountManagerConsole().askDeleteUser()) {
            for (Habit habit : new HabitManager().findAllHabits(user.getId())) {
                for (Status status : new StatusManager().findAllStatusesOfHabit(habit.getId())) {
                    new StatusManager().deleteStatus(habit.getId());
                }
                new HabitManager().deleteHabit(user.getId());
            }
            new UserDAO().delete(user);
            UserAccountManagerConsole.sayDelete();
            return true;
        }
        return false;
    }

    public static boolean isAuthorized(User user) {
        if (user != null) {
            return true;
        }
        UserAccountManagerConsole.sayNotAuthorized();
        return false;
    }

    public User doLogOut(User user) {
        UserAccountManagerConsole.sayAlreadyAuthorized();
        int choiceLogOut = CheckWriting.isRightOperation(1, 1, 0);
        if (choiceLogOut == 1) {
            user = null;
            UserAccountManagerConsole.sayLogOut();
        }
        return user;
    }

    public User doAdmin(User user) {
        if (user.getEmail().equals("admin@mail.com")) {
            Admin admin = new Admin(user);
            new MenuAdmin().doAdministration(admin);
            AdminConsole.sayGoodbye();
            user = null;
        }
        return user;
    }
}
