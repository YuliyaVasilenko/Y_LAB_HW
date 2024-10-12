package habitsApp;

import java.util.Arrays;

public enum OperationOnHabitsInformation {
    CREATE_HABIT(1, "If you want to create new habit"),
    UPDATE_HABIT(2, "If you want to edit some habit"),
    DELETE_HABIT(3, "If you want to delete some habit"),
    SHOW_ALL_HABITS(4, "If you want to look at all your habits"),
    RETURN_TO_MENU(5, "If you want to exit to menu"),
    EXIT(6, "If you want to exit");

    private final int numberOfOperation;
    private final String description;

    OperationOnHabitsInformation(int numberOfOperation, String description) {
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
        for (OperationOnHabitsInformation operation : OperationOnHabitsInformation.values())
            System.out.printf("%-50s press %d %n", operation.getDescription(), operation.getNumberOfOperation());
    }
}
