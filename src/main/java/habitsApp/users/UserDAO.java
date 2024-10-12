package habitsApp.users;

import habitsApp.HabitApp;

public class UserDAO {
    public User find(String email) {
        User userFound = null;
        if (HabitApp.dataBaseUsers.containsKey(email)) {
            for (String key : HabitApp.dataBaseUsers.keySet()) {
                if (HabitApp.dataBaseUsers.get(key).getEmail().equals(email)) {
                    userFound = HabitApp.dataBaseUsers.get(key);
                }
            }
        }
        return userFound;
    }

    public boolean createUser(User user) {
        HabitApp.dataBaseUsers.put(user.getEmail(), user);
        return true;
    }

    public User update(User user) {
        HabitApp.dataBaseUsers.put(user.getEmail(), user);
        return user;
    }

    public boolean deleteUser(User user) {
        HabitApp.dataBaseUsers.remove(user.getEmail());
        return true;
    }
}
