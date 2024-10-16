package habitsApp.users;

import habitsApp.dao.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserAccountManagerTest {
    //public static Map<Integer, User> dataBaseUsers = new MenuManager().getDataBaseSaved("allUsersDB");

    @Test
    void doAuthorizationOrRegistration() {
    }

    @Test
    void updateUser() {

        /*InputStream is = System.in;
        ByteArrayInputStream bs = new ByteArrayInputStream("String".getBytes());
        System.setIn(bs);*/

        User user1 = new User("123@mail.ru", "Ann", "123");
        /*new UserDAO().create(user1);
        User user2 = new User("123@mail.ru", "Kate", "123");
        User user3 = new UserDAO().update(user1);
        Assertions.assertEquals(user1, user3);*/
        //String newName = "Kate";
        /*User user2 = new UserAccountManager().updateUser(user1);
        Assertions.assertEquals(user1.getName(), user2.getName());*/

    }

    @Test
    void deleteUser() {
    }

    @Test
    void isAuthorized() {
    }
}