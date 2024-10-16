package habitsApp.dao;

import habitsApp.menu.HabitApp;
import habitsApp.users.User;

/**
 * @author YuliyaVasilenko
 * @date 12-10-2024
 * Description: this class describes the interaction between a user's entity and a database
 */
public class UserDAO extends EntityDAO<User>{
    /**
     * @ Method Name: find
     * @ Description: search for a user entity in database by a unique field 'id'
     * @ param      : [int]
     * @ return     : habitsApp.users.User
     */
    @Override
    public User find(int id) {
        User userFound = null;
        if (HabitApp.dataBaseUsers.containsKey(id)) {
            for (Integer key : HabitApp.dataBaseUsers.keySet()) {
                if (key == id) {
                    userFound = HabitApp.dataBaseUsers.get(key);
                    break;
                }
            }
        }
        return userFound;
    }

    /**
     * @ Method Name: find
     * @ Description: search for a user entity in database by a unique field 'email'
     * @ param      : [java.lang.String]
     * @ return     : habitsApp.users.User
     */
    public User find(String email) {
        User userFound = null;
        for (Integer key : HabitApp.dataBaseUsers.keySet()) {
            if (HabitApp.dataBaseUsers.get(key).getEmail().equals(email)) {
                userFound = HabitApp.dataBaseUsers.get(key);
                break;
            }
        }
        return userFound;
    }

    /**
     * @ Method Name: create
     * @ Description: creating a new user in the database
     * @ param      : [habitsApp.users.User]
     * @ return     : boolean
     */
    @Override
    public boolean create(User user) {
        HabitApp.dataBaseUsers.put(user.getId(), user);
        return true;
    }

    /**
     * @ Method Name: update
     * @ Description: updating user data in the database
     * @ param      : [habitsApp.users.User]
     * @ return     : habitsApp.users.User
     */
    @Override
    public User update(User user) {
        HabitApp.dataBaseUsers.put(user.getId(), user);
        user = new UserDAO().find(user.getId());
        return user;
    }

    /**
     * @ Method Name: delete
     * @ Description: deleting a user from the database
     * @ param      : [habitsApp.users.User]
     * @ return     : boolean
     */
    @Override
    public boolean delete(User user) {
        HabitApp.dataBaseUsers.remove(user.getId());
        return true;
    }

    /**
     * @ Method Name: checkBlocking
     * @ Description: checking whether a user in the database of blocked users
     * @ param      : [int]
     * @ return     : boolean
     */
    public boolean checkBlocking(int id) {
        return HabitApp.dataBaseBlockedUsers.containsKey(id);
    }
}
