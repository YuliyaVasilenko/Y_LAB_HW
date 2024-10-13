package habitsApp.menu;

import java.util.Arrays;

public enum OperationOnHabitsControl {
    SET_HABIT_STATUS(1, "If you want to change habit's status"),
    SHOW_HABIT_HISTORY(2, "If you want to show some habit's history"),
    SHOW_HABIT_STATISTICS(3, "If you want to delete some habit"),
    RETURN_TO_MENU(4, "If you want to exit to menu"),
    EXIT(5, "If you want to exit");

    private final int numberOfOperation;
    private final String description;

    OperationOnHabitsControl(int numberOfOperation, String description) {
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
        System.out.println("Please chose an operation: " + Arrays.toString(OperationOnHabitsControl.values()));
        for (OperationOnHabitsControl operation : OperationOnHabitsControl.values())
            System.out.printf("%-50s press %d %n", operation.getDescription(), operation.getNumberOfOperation());
    }
}
