package habitsApp.reminder;

import habitsApp.habit.Habit;

import java.util.List;

public class ReminderConsole {
    public static void printReminder(List<Habit> habitList) {
        System.out.println("Today you have such habit that you have to do");
        System.out.printf("%-20s%-20s%s%n", "name", "description", "status");
        /*for (Habit habit : habitList) {
            System.out.printf("%-20s%-20s%s%n", habit.getName(), habit.getDescription(),
                    habit.getProgress().getExecution().peek().isHasDone());
        }*/
    }
}
