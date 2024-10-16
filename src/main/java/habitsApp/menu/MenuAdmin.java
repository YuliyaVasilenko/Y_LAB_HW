package habitsApp.menu;

import habitsApp.users.Admin;

public class MenuAdmin {
    public void doAdministration(Admin admin) {
        int choiceWork;
        do {
            OperationInAdminMenu.showOperation();
            choiceWork = CheckWriting.isRightOperation(1, OperationInAdminMenu.values().length, OperationInAdminMenu.EXIT.ordinal() + 1);
            switch (choiceWork) {
                case 1 -> admin.showAllUsers();
                case 2 -> admin.deleteUser();
                case 3 -> admin.blockUser();
                case 4 -> admin.unBlockUser();
            }
        } while (choiceWork != OperationInAdminMenu.EXIT.ordinal() + 1);

    }
}
