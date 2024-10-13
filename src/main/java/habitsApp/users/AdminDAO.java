package habitsApp.users;

import habitsApp.menu.HabitApp;

import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    public List<User> findAllUsers() {
        List<User> list = new ArrayList<>();
        for (Integer id : HabitApp.dataBaseUsers.keySet()) {
            list.add(HabitApp.dataBaseUsers.get(id));
        }
        return list;
    }

    public List<User> findBlockedUsers() {
        List<User> list = new ArrayList<>();
        for (Integer id : HabitApp.dataBaseBlockedUsers.keySet()) {
            list.add(HabitApp.dataBaseBlockedUsers.get(id));
        }
        return list;
    }

    public boolean deleteUser(String email) {
        boolean wasDeleted = false;
        for (Integer id : HabitApp.dataBaseUsers.keySet()) {
            if (HabitApp.dataBaseUsers.get(id).getEmail().equals(email)) {
                HabitApp.dataBaseUsers.remove(id);
                wasDeleted = true;
            }
        }
        return wasDeleted;
    }

    public boolean blockUser(String email) {
        boolean wasBlocked = false;
        for (Integer id : HabitApp.dataBaseUsers.keySet()) {
            User userToBlock = HabitApp.dataBaseUsers.get(id);
            if (userToBlock.getEmail().equals(email)) {
                HabitApp.dataBaseBlockedUsers.put(id, userToBlock);
                wasBlocked = true;
            }
        }
        return wasBlocked;
    }

    public boolean unBlockUser(String email) {
        boolean wasUnBlocked = false;
        for (Integer id : HabitApp.dataBaseBlockedUsers.keySet()) {
            User userToUnBlock = HabitApp.dataBaseBlockedUsers.get(id);
            if (userToUnBlock.getEmail().equals(email)) {
                HabitApp.dataBaseBlockedUsers.remove(id);
                wasUnBlocked = true;
            }
        }
        return wasUnBlocked;
    }
}
