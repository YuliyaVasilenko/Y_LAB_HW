package habitsApp;

import java.util.Arrays;

public enum OperationInTheMenu {
    AUTHORIZATION_AND_REGISTRATION(1, "If you want to log in or register"),
    CHANGE_DATA(2, "If you want to edit your profile"),
    DELETE_ACCOUNT(3, "If you want to remove your profile"),
    HABIT_INFORMATION(4, "If you want to move on to habit information"),
    HABIT_CONTROL(5, "If you want to move on to habit control"),
    EXIT(6, "If you want to exit");

    private final int numberOfOperation;
    private final String description;

    OperationInTheMenu(int numberOfOperation, String description) {
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
        System.out.println("Please chose an operation: " + Arrays.toString(OperationInTheMenu.values()));
        for (OperationInTheMenu operationInTheMenu : OperationInTheMenu.values())
            System.out.printf("%-50s press %d %n", operationInTheMenu.getDescription(), operationInTheMenu.getNumberOfOperation());
    }
}
