package habitsApp.status;

import habitsApp.dao.HabitDAO;
import habitsApp.dao.StatusDAO;
import habitsApp.habit.HabitManagerConsole;
import habitsApp.habit.Habit;

import java.time.LocalDate;
import java.util.List;

public class StatusManager {
    public List<Status> findAllStatusesOfHabit(int habitId) {
        return new StatusDAO().findAllStatusesOfHabits(habitId);
    }

    public boolean createStatus(int habitId) {
        Status newStatus = new Status(habitId, LocalDate.now(), false);
        return new StatusDAO().create(newStatus);
    }

    public boolean setStatus(int habitId) {
        boolean hasSet = true;
        int choiceStatus = new StatusConsole().askWhatStatus();
        if (choiceStatus == 0) {
            return false;
        }
        boolean hasDone = choiceStatus == 1;
        Status statusToUpdate = new StatusDAO().find(habitId, LocalDate.now());
        statusToUpdate.setHasDone(hasDone);
        new StatusDAO().update(statusToUpdate);
        StatusConsole.sayStatus();
        return hasSet;
    }

    /*public void getStatistics(Habit habit) {
        LocalDate date = new HabitManagerConsole().askStartDate();
        new StatusConsole().printHabitStatus(habit.getName(), new HabitDAO().filterByDate(date));
    }*/

    public boolean deleteStatus(int habitId) {
        Status statusToDelete = new StatusDAO().find(habitId);
        new StatusDAO().delete(statusToDelete);
        return true;
    }
}
