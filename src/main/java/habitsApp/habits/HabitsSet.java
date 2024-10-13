package habitsApp.habits;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HabitsSet {
    private Map<Integer, Habit> allHabits = new HashMap<>();

    public Map<Integer, Habit> getHabits() {
        return allHabits;
    }

    public void setHabits(Map<Integer, Habit> habits) {
        this.allHabits = habits;
    }

    public Habit findHabit(String name) {
        for (Integer id : allHabits.keySet()) {
            if (allHabits.get(id).getName().equals(name)) {
                return allHabits.get(id);
            }
        }
        return null;
    }

    public Habit findHabit(int id) {
        if (allHabits.containsKey(id)) {
            for (Integer key : allHabits.keySet()) {
                if (key == id) {
                    return allHabits.get(id);
                }
            }
        }
        return null;
    }

    public boolean createHabit(Habit habit) {
        allHabits.put(habit.getId(), habit);
        return true;
    }

    public Habit updateHabit(Habit habit) {
        allHabits.put(habit.getId(), habit);
        habit = findHabit(habit.getId());
        return habit;
    }

    public boolean deleteHabit(Habit habit) {
        allHabits.remove(habit.getId());
        return true;
    }

    public List<Habit> showAllHabits() {
        List<Habit> list = new ArrayList<>();
        for (Integer id : allHabits.keySet()) {
            list.add(allHabits.get(id));
        }
        return list;
    }

    public List<Habit> filterHabitsByDate(LocalDate date) {
        List<Habit> list = new ArrayList<>();
        for (Integer id : allHabits.keySet()) {
            if (allHabits.get(id).getDateStart().isAfter(date) ) {
                list.add(allHabits.get(id));
            }
        }
        return list;
    }

    public List<Habit> filterByStatus(boolean status) {
        List<Habit> list = new ArrayList<>();
        for (Integer id : allHabits.keySet()) {
            if (allHabits.get(id).getProgress().filterByStatus(status) != null) {
                list.add(allHabits.get(id));
            }
        }
        return list;
    }
}
