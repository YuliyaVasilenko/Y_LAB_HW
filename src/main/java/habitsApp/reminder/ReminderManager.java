package habitsApp.reminder;

import habitsApp.habits.Habit;
import habitsApp.status.Status;
import habitsApp.users.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReminderManager {

    public void updateAllStatus(User user) {
        List<Habit> listAllHabits = user.getHabits().showAllHabits();
        for (Habit habit : listAllHabits) {
            //chose last modification status from each habit
            Status lastStatus = habit.getProgress().getExecution().peek();
            if (lastStatus != null && !lastStatus.getDate().isBefore(LocalDate.now())) {
                LocalDate newDate = lastStatus.getDate().plusDays(habit.getPeriod());
                Status newStatus = new Status(newDate, false);
                habit.getProgress().getExecution().offer(newStatus);
            }
        }
    }

    public List<Habit> findAllReminder(User user) {
        List<Habit> resultList = new ArrayList<>();
        List<Habit> listAllHabits = user.getHabits().showAllHabits();
        for (Habit habit : listAllHabits) {
            Status lastStatus = habit.getProgress().getExecution().peek();
            if (lastStatus.getDate().equals(LocalDate.now())) {
                resultList.add(habit);
            }
        }
        return resultList;
    }

    public void printReminder(User user) {
        ReminderConsole.printReminder(findAllReminder(user));
    }
}
