package habitsApp.menu;

import habitsApp.users.Admin;

public class MenuAdmin {
    public void doAdministration(Admin admin) {
        int choiceWork;
        do {
            OperationAdmin.showOperation();
            choiceWork = CheckWriting.isRightOperation(1, OperationAdmin.values().length, OperationAdmin.EXIT.ordinal() + 1);
            switch (choiceWork) {
                case 1 -> admin.showAllUsers();
                case 2 -> admin.deleteUser();
                case 3 -> admin.blockUser();
                case 4 -> admin.unBlockUser();
            }
        } while (choiceWork != OperationAdmin.EXIT.ordinal() + 1);

    }
}
