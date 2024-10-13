package habitsApp.menu;

import habitsApp.users.*;

import java.util.Map;

public class MenuManager {
    public Map<Integer, User> getDataBaseSaved(String nameDB) {
        UsersDataBase dataBaseSaved = UsersDataBase.deserialization(nameDB);
        if (dataBaseSaved == null) {
            dataBaseSaved = new UsersDataBase();
        }
        return dataBaseSaved.getdb();
    }

    public void saveDataBase(Map<Integer, User> dataBaseUsers, String nameDB) {
        UsersDataBase dataBaseToSave = new UsersDataBase();
        dataBaseToSave.setDb(dataBaseUsers);
        dataBaseToSave.serialization(nameDB);
    }

    public User doAuthorizationOrRegistration(User user) {
        if (!UserManager.isAuthorized(user)) {
            user = UserManager.doAuthorizationOrRegistration();
            if (user.getEmail().equals("admin@mail.com")) {
                Admin admin = new Admin(user);
                new MenuAdmin().doAdministration(admin);
                AdminConsole.sayGoodbye();
                user = null;
            }
        } else {
            MenuConsole.sayAlreadyAuthorized();
            int choiceLogOut = CheckWriting.isRightOperation(1, 1, 0);
            if (choiceLogOut == 1) {
                user = null;
                MenuConsole.sayLogOut();
            }
        }
        return user;
    }

    public User doUpdateUser(User user) {
        if (UserManager.isAuthorized(user)) {
            UserManager.updateUser(user);
        } else {
            MenuConsole.sayNotAuthorized();
        }
        return user;
    }

    public User doDeleteUser(User user) {
        if (UserManager.isAuthorized(user)) {
            UserManager.deleteUser(user);
            user = null;
        } else {
            MenuConsole.sayNotAuthorized();
        }
        return user;
    }


}
