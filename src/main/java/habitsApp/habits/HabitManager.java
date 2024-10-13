package habitsApp.habits;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class HabitManager {
    public Habit choseHabit(HabitsSet habits) {
        new HabitConsole().printHabits(habits.showAllHabits());
        int index = new HabitConsole().choseHabitIndex();
        if (index == 0) {
            return null;
        }
        return habits.findHabit(index);
    }
    public boolean createHabit(HabitsSet habits) {
        boolean hasCreated = false;
        String habitName = new HabitConsole().askHabitName();
        if (habitName == null) {
            return false;
        }
        if (habits.findHabit(habitName) != null) {
            HabitConsole.sayHabitExists();
        }
        else {
            Habit habit = new HabitConsole().createHabit(habitName);
            if (habits.createHabit(habit)) {
                habit = habits.findHabit(habit.getId());
                if (habit != null) {
                    HabitConsole.sayHabitCreates();
                    hasCreated = true;
                }
            }
        }
        return hasCreated;
    }

    public boolean updateHabit(HabitsSet habits) {
        boolean hasChanged = false;
        Habit habitUpdated = choseHabit(habits);
        if (habitUpdated == null) {
            return false;
        }
        int fieldUpdate = new HabitConsole().askFieldUpdateHabit();
        if (fieldUpdate != 0) {
            String newData = new HabitConsole().askDataUpdateHabit(fieldUpdate);
            if (newData != null) {
                switch (fieldUpdate) {
                    case 1 -> habitUpdated.setName(newData);
                    case 2 -> habitUpdated.setDescription(newData);
                    case 3 -> habitUpdated.setPeriod(Integer.parseInt(newData));
                }
                habits.updateHabit(habitUpdated);
                hasChanged = true;
                HabitConsole.sayHabitUpdate();
            }
        }
        return hasChanged;
    }

    public boolean deleteHabit(HabitsSet habits) {
        boolean hasDeleted = false;
        if (new HabitConsole().askDeleteHabit()) {
            Habit habitDeleted = choseHabit(habits);
            if (habitDeleted != null && habits.deleteHabit(habitDeleted)) {
                hasDeleted = true;
            }
        }
        return hasDeleted;
    }

    public void showAndSortAllHabits(HabitsSet habits) {
        HabitConsole hs = new HabitConsole();
        hs.printHabits(habits.showAllHabits());
        int choiceSort = hs.askHowSort();
        switch (choiceSort) {
            case 1 -> {
                LocalDate dateStart = hs.askStartDate();
                if (dateStart==null) {
                    return;
                }
                Comparator<Habit> comparator = Comparator.comparing(Habit::getDateStart).thenComparing(Habit::getName);
                SortedSet<Habit> set = new TreeSet<>(comparator);
                set.addAll(habits.filterHabitsByDate(dateStart));
                hs.printHabits(set.stream().toList());
            }
            case 2 -> {
                int status = hs.askStatusToShow();
                boolean statusToShow = status == 1;
                if (status == 0) {
                    return;
                }
                hs.printHabits(habits.filterByStatus(statusToShow));
            }
        }
    }
}

