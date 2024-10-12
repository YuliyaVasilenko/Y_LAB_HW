package habitsApp.users;

import habitsApp.CheckWriting;

public class Admin extends User {
    public Admin(User user) {
        super(user.getEmail(), user.getName(), user.getPassword());
    }

    public void findAllUsers() {
        System.out.println(new AdminDAO().findAllUsers());
    }

    public boolean deleteUser() {
        boolean wasDeleted = false;
        findAllUsers();
        System.out.println("Which user will you delete? Write user's email");
        String email = CheckWriting.checkEmail();
        if (email != null) {
            wasDeleted = new AdminDAO().deleteUser(email);
        }
        return wasDeleted;
    }
}
