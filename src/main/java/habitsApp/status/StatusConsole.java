package habitsApp.status;

import habitsApp.menu.CheckWriting;

import java.time.format.DateTimeFormatter;
import java.util.Deque;

public class StatusConsole {
    public int askWhatStatus() {
        System.out.println("To set status 'done' press 1, to set status 'not done' press 2, to exit to menu press 0");
        return CheckWriting.isRightOperation(1, 2,0);
    }

    public void printHabitStatus(String habitName, Deque<Status> progress) {
        if (progress.size() == 0) {
            System.out.println("You haven't made any progress on this habit yet");
            return;
        }
        System.out.println("Your progress on habit " + habitName + ":");
        System.out.printf("%-20s%-20s%n", "date", "status");
        for (Status status : progress) {
            String hasDone = status.isHasDone() ? "Done" : "Not done";
            System.out.printf("%-20s%-20s%n", status.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), hasDone);
        }
    }

    public static void sayStatus() {
        System.out.println("Success! The status of the habit has been updated.");
    }
}
