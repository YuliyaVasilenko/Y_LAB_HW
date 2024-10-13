package habitsApp.users;

public class UserManager {

    public static User doAuthorizationOrRegistration() {
        String email = new UserConsole().askEmail();
        if (email == null) {
            return null;
        }
        User user = new UserDAO().find(email);
        if (user != null) { //authorization
            if (!new UserConsole().doAuthorization(user)) {
                return null;
            } else if (new UserDAO().checkBlocking(user.getId())) {
                UserConsole.sayBlocking();
                return null;
            }
        } else { //registration
            user = new UserConsole().doRegistration(email);
            UserDAO userDAO = new UserDAO();
            if (userDAO.createUser(user)) {
                user = userDAO.find(email);
            }
        }
        UserConsole.sayGreeting(user);
        return user;
    }

    public static boolean updateUser(User user) {
        boolean hasChanged = false;
        int fieldUpdate = new UserConsole().askFieldUpdate();
        if (fieldUpdate != 0) {
            String newData = new UserConsole().askDataUpdate(fieldUpdate);
            if (newData != null) {
                switch (fieldUpdate) {
                    case 1 -> user.setEmail(newData);
                    case 2 -> user.setName(newData);
                    case 3 -> user.setPassword(newData);
                }
                new UserDAO().update(user);
                hasChanged = true;
                UserConsole.sayUpdate();
            }
        }
        return hasChanged;
    }

    public static boolean deleteUser(User user) {
        if (new UserConsole().askDeleteUser()) {
            new UserDAO().deleteUser(user);
            UserConsole.sayDelete();
            return true;
        }
        return false;
    }

    public static boolean isAuthorized(User user) {
        return user != null;
    }
}
