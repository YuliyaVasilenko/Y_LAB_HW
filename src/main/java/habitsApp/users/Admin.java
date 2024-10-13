package habitsApp.users;

public class Admin extends User {

    public Admin(User user) {
        super(user.getEmail(), user.getName(), user.getPassword());
    }

    public void showAllUsers() {
        System.out.println(new AdminDAO().findAllUsers());
    }

    public void showBlockedUsers() {
        System.out.println(new AdminDAO().findBlockedUsers());
    }

    public boolean deleteUser() {
        boolean wasDeleted = false;
        showAllUsers();
        String email = new AdminConsole().askEmailUser();
        if (email != null) {
            wasDeleted = new AdminDAO().deleteUser(email);
        }
        return wasDeleted;
    }

    public boolean blockUser() {
        boolean wasBlocked = false;
        showAllUsers();
        String email = new AdminConsole().askEmailUser();
        if (email != null) {
            wasBlocked = new AdminDAO().blockUser(email);
        }
        return wasBlocked;
    }

    public boolean unBlockUser() {
        boolean wasUnBlock = false;
        showBlockedUsers();
        String email = new AdminConsole().askEmailUser();
        if (email != null) {
            wasUnBlock = new AdminDAO().unBlockUser(email);
        }
        return wasUnBlock;
    }
}
