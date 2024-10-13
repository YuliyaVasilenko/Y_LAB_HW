package habitsApp.status;

import habitsApp.habits.HabitConsole;
import habitsApp.habits.Habit;

import java.time.LocalDate;

public class ProgressManager {
    public boolean setStatus(Habit habit) {
        boolean hasSet = true;
        int choiceStatus = new ProgressConsole().askWhatStatus();
        if (choiceStatus == 0) {
            return false;
        }
        boolean whatStatus = choiceStatus == 1;
        habit.getProgress().getExecution().peek().setHasDone(whatStatus);
        ProgressConsole.sayStatus();
        return hasSet;
    }

    public void getStatistics(Habit habit) {
        LocalDate date = new HabitConsole().askStartDate();
        new ProgressConsole().printHabitStatus(habit.getName(), habit.getProgress().filterByDate(date));
    }


}
