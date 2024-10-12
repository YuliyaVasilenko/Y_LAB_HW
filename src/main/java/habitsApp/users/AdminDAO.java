package habitsApp.users;

import habitsApp.HabitApp;

import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    public List<User> findAllUsers() {
        List<User> list = new ArrayList<>();
        for (String userEmail : HabitApp.dataBaseUsers.keySet()) {
            list.add(HabitApp.dataBaseUsers.get(userEmail));
        }
        return list;
    }

    public boolean deleteUser(String email) {
        User userToDelete = null;
        boolean wasDeleted = false;
        for (String key : HabitApp.dataBaseUsers.keySet()) {
            if (HabitApp.dataBaseUsers.get(key).getEmail().equals(email)) {
                HabitApp.dataBaseUsers.remove(email);
                wasDeleted = true;
            }
        }
        return wasDeleted;
    }
}
