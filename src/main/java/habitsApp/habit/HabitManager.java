package habitsApp.habit;

import habitsApp.dao.HabitDAO;
import habitsApp.status.StatusManager;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class HabitManager {
    public List<Habit> findAllHabits(int userId) {
        return new HabitDAO().findAllHabits(userId);
    }

    public Habit choseHabit(int userId) {
        new HabitManagerConsole().printHabits(findAllHabits(userId));
        int id = new HabitManagerConsole().choseHabitId();
        if (id == 0) {
            return null;
        }
        return new HabitDAO().find(id);
    }

    public boolean createHabit(int userId) {
        boolean hasCreated = false;
        String habitName = new HabitManagerConsole().askHabitName();
        if (habitName == null) {
            return false;
        }
        if (new HabitDAO().find(userId, habitName) != null) {
            HabitManagerConsole.sayHabitExists();
        }
        else {
            Habit habit = new HabitManagerConsole().createHabit(userId, habitName);
            if (new HabitDAO().create(habit)) {
                habit = new HabitDAO().find(habit.getId());
                if (habit != null) {
                    new StatusManager().createStatus(habit.getId());
                    HabitManagerConsole.sayHabitCreates();
                    hasCreated = true;
                }
            }
        }
        return hasCreated;
    }

    public boolean updateHabit(int userId) {
        boolean hasChanged = false;
        Habit habitUpdated = choseHabit(userId);
        if (habitUpdated == null) {
            return false;
        }
        int fieldUpdate = new HabitManagerConsole().askFieldUpdateHabit();
        if (fieldUpdate != 0) {
            String newData = new HabitManagerConsole().askDataUpdateHabit(fieldUpdate);
            if (newData != null) {
                switch (fieldUpdate) {
                    case 1 -> habitUpdated.setName(newData);
                    case 2 -> habitUpdated.setDescription(newData);
                    case 3 -> habitUpdated.setPeriod(Integer.parseInt(newData));
                }
                new HabitDAO().update(habitUpdated);
                HabitManagerConsole.sayHabitUpdate();
                hasChanged = true;
            }
        }
        return hasChanged;
    }

    public boolean deleteHabit(int userId) {
        boolean hasDeleted = false;
        if (new HabitManagerConsole().askDeleteHabit()) {
            Habit habitDeleted = choseHabit(userId);
            if (habitDeleted != null && new HabitDAO().delete(habitDeleted)) {
                hasDeleted = true;
            }
        }
        return hasDeleted;
    }

    public void showAndSortAllHabits(int userId) {
        HabitManagerConsole hs = new HabitManagerConsole();
        hs.printHabits(findAllHabits(userId));
        int choiceSort = hs.askHowSort();
        switch (choiceSort) {
            case 1 -> {
                LocalDate dateStart = hs.askStartDate();
                if (dateStart==null) {
                    return;
                }
                Comparator<Habit> comparator = Comparator.comparing(Habit::getDateStart).thenComparing(Habit::getName);
                SortedSet<Habit> set = new TreeSet<>(comparator);
                set.addAll(new HabitDAO().filterHabitsByDate(userId, dateStart));
                hs.printHabits(set.stream().toList());
            }
            case 2 -> {
                int status = hs.askStatusToShow();
                boolean statusToShow = status == 1;
                if (status == 0) {
                    return;
                }

                //hs.printHabits(new HabitDAO().filterByStatus(userId, statusToShow));
            }
        }
    }
}

