package habitsApp.users.habits.status;

import habitsApp.HabitApp;
import habitsApp.users.habits.Habit;

import java.time.LocalDate;

public class ExecutionDAO {
    public boolean setStatus(boolean bool) {
        for (LocalDate date : HabitApp.dataBaseStatus.keySet()) {
            if (date.equals(LocalDate.now())) {
                HabitApp.dataBaseStatus.put(date, bool);
                return true;
            }
        }
        return false;
    }

}
