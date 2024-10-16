package habitsApp.menu;

import java.util.Arrays;

public enum OperationInAdminMenu {
    SHOW_ALL_USERS(1, "If you want to show all user"),
    DELETE_USER(2, "If you want to delete user"),
    BLOCK_USER(3, "If you want to block user"),
    UNBLOCK_USER(4, "If you want to unblock user"),
    EXIT(5, "If you want to exit");

    private final int numberOfOperation;
    private final String description;

    OperationInAdminMenu(int numberOfOperation, String description) {
        this.numberOfOperation = numberOfOperation;
        this.description = description;
    }

    public int getNumberOfOperation() {
        return numberOfOperation;
    }
    public String getDescription() {
        return description;
    }

    public static void showOperation() {
        System.out.println("Please chose an operation: " + Arrays.toString(OperationInAdminMenu.values()));
        for (OperationInAdminMenu operationAdmin : OperationInAdminMenu.values())
            System.out.printf("%-50s press %d %n", operationAdmin.getDescription(), operationAdmin.getNumberOfOperation());
    }
}
