package habitsApp.users;

import habitsApp.menu.HabitApp;

public class UserDAO {
    public User find(int id) {
        User userFound = null;
        if (HabitApp.dataBaseUsers.containsKey(id)) {
            for (Integer key : HabitApp.dataBaseUsers.keySet()) {
                if (key == id) {
                    userFound = HabitApp.dataBaseUsers.get(key);
                }
            }
        }
        return userFound;
    }

    public User find(String email) {
        User userFound = null;
        for (Integer key : HabitApp.dataBaseUsers.keySet()) {
            if (HabitApp.dataBaseUsers.get(key).getEmail().equals(email)) {
                userFound = HabitApp.dataBaseUsers.get(key);
            }
        }
        return userFound;
    }

    public boolean checkBlocking(int id) {
        return HabitApp.dataBaseBlockedUsers.containsKey(id);
    }

    public boolean createUser(User user) {
        HabitApp.dataBaseUsers.put(user.getId(), user);
        return true;
    }

    public User update(User user) {
        HabitApp.dataBaseUsers.put(user.getId(), user);
        user = new UserDAO().find(user.getId());
        return user;
    }

    public boolean deleteUser(User user) {
        HabitApp.dataBaseUsers.remove(user.getId());
        return true;
    }
}
