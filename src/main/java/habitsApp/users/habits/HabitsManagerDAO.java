package habitsApp.users.habits;

import habitsApp.CheckWriting;
import habitsApp.HabitApp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class HabitsManagerDAO {

    public Habit findHabit(String name) {
        for (Habit habit : HabitApp.dataBaseHabits) {
            if (habit.getName().equals(name)) {
                return habit;
            }
        }
        return null;
    }

    public Habit findHabit(int index) {
        return HabitApp.dataBaseHabits.get(index);
    }

    public int choseHabitIndex() {
        showAllHabits();
        System.out.println("Write the index of the habit you want to edit");
        int index = CheckWriting.isRightOperation(1, HabitApp.dataBaseHabits.size(), 0);
        if (index == 0) return -1;
        else return index-1;
    }

    public boolean create(Habit habit) {
        HabitApp.dataBaseHabits.add(habit);
        return true;
    }

    public Habit update(int index, String field, int numberOfField) {
        Habit habitUpdated = findHabit(index);
        switch (numberOfField) {
            case 1 -> habitUpdated.setName(field);
            case 2 -> habitUpdated.setDescription(field);
            case 3 -> habitUpdated.setPeriod(Integer.parseInt(field));
        }
        return habitUpdated;
    }

    public Habit delete(int index) {
        Habit habitDeleted = findHabit(index);
        HabitApp.dataBaseHabits.remove(habitDeleted);
        return habitDeleted;
    }

    public void showAllHabits() {
        if (HabitApp.dataBaseHabits.size() == 0) {
            System.out.println("You don't have any habits yet");
            return;
        }
        System.out.println("You have habits:");
        System.out.printf("%-10s%-20s%-20s%s%n", "index", "name", "start at:", "description");
        for (Habit habit : HabitApp.dataBaseHabits) {
            System.out.printf("%-10d%-20s%-20s%s%n", HabitApp.dataBaseHabits.indexOf(habit) + 1, habit.getName(),
                    habit.getDateStart().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), habit.getDescription());
        }
    }

    public void showAndSortAllHabits() {
        showAllHabits();
        System.out.println("If you want to filter habits by the creation date press 1, " +
                "to filter habits by their execution status press 2, to return to menu press 0");
        int choice = CheckWriting.isRightOperation(1, 2, 0);
        if (choice == 1) {
            System.out.println("Write the date from which you want to show all the habits");
            System.out.println("Write the starting year");
            int year = CheckWriting.isRightOperation(2024, 2030, 0);
            if (year == 0) return;
            System.out.println("Write the staring month");
            int month = CheckWriting.isRightOperation(1, 12, 0);
            if (month == 0) return;
            System.out.println("Write the starting day");
            int day = CheckWriting.isRightOperation(1, 31, 0);
            if (day == 0) return;
            LocalDate dateStart = LocalDate.of(year, month, day);
            Comparator<Habit> comparator = Comparator.comparing(Habit::getDateStart).thenComparing(Habit::getName);
            SortedSet<Habit> set = new TreeSet<>(comparator);
            for (Habit habit : HabitApp.dataBaseHabits) {
                if (habit.getDateStart().isAfter(dateStart)) {
                    set.add(habit);
                }
            }
            System.out.printf("%-10s%-20s%-20s%s%n", "index", "name", "start at:", "description");
            for (Habit habit : set) {
                System.out.printf("%-10d%-20s%-20s%s%n", HabitApp.dataBaseHabits.indexOf(habit) + 1, habit.getName(),
                        habit.getDateStart().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), habit.getDescription());
            }
        } else if (choice == 2) {
            System.out.println("To show habits with status completed on today press 1, " +
                    "with status uncompleted for today press 2, to exit to menu press 0");
            int choiceStatus = CheckWriting.isRightOperation(1, 2, 0);
            SortedSet<Habit> set = new TreeSet<>();
            /*for (Habit habit : habits) {
                if (habit.getStatistics().)
            }*/

        }
    }
}
