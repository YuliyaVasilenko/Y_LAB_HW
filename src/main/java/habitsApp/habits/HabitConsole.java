package habitsApp.habits;

import habitsApp.menu.CheckWriting;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HabitConsole {
    public String askHabitName() {
        return CheckWriting.checkWord("habit's name");
    }

    public Habit createHabit(String habitName) {
        String habitDescription = CheckWriting.checkWord("habit's description");
        if (habitDescription == null) {
            return null;
        }
        System.out.println("Write the number of days during which this habit is repeated from 1 day to 30 days");
        int habitPeriod = CheckWriting.isRightOperation(1, 30, 0);
        if (habitPeriod == 0) {
            return null;
        }
        return new Habit(habitName, habitDescription, habitPeriod);
    }

    public int choseHabitIndex() {
        System.out.println("Write the index of the habit you want to edit");
        return CheckWriting.isRightOperation(1, 100, 0);
    }

    public int askFieldUpdateHabit() {
        System.out.println("If you want to edit the habit's name press 1, to edit the habit's description press 2, " +
                "to edit the habit's number of days during which this habit is repeated press 3, to exit to menu press 0");
        return CheckWriting.isRightOperation(1, 3, 0);
    }

    public String askDataUpdateHabit(int choice) {
        String newData;
        if (choice == 3) {
            int newPeriod = CheckWriting.isRightOperation(1, 30, 0);
            newData = Integer.toString(newPeriod);
        } else {
            newData = CheckWriting.checkWord("new data");
        }
        return newData;
    }

    public boolean askDeleteHabit() {
        System.out.println("Are you sure you want to delete the habit? All information and progress will be lost. " +
                "To permanently delete this habit press 1, to cancel press 0");
        int confirmChoice = CheckWriting.isRightOperation(1, 1, 0);
        return confirmChoice == 1;
    }

    public int askHowSort() {
        System.out.println("If you want to filter habits by the creation date press 1, " +
                "to filter habits by their execution status press 2, to return to menu press 0");
        return CheckWriting.isRightOperation(1, 2, 0);
    }

    public LocalDate askStartDate() {
        System.out.println("Write the date from which you want to show all information");
        System.out.println("Write the starting year");
        int year = CheckWriting.isRightOperation(2024, 2030, 0);
        if (year == 0) {
            return null;
        }
        System.out.println("Write the starting month");
        int month = CheckWriting.isRightOperation(1, 12, 0);
        if (month == 0) {
            return null;
        }
        System.out.println("Write the starting day");
        int day = CheckWriting.isRightOperation(1, 31, 0);
        if (day == 0) {
            return null;
        }
        return LocalDate.of(year, month, day);
    }

    public int askStatusToShow() {
        System.out.println("To show habits with status completed on today press 1, " +
                "with status uncompleted for today press 2, to exit to menu press 0");
        return CheckWriting.isRightOperation(1, 2, 0);
    }

    public void printHabits(List<Habit> habitList) {
        if (habitList.size() == 0) {
            System.out.println("You don't have any habits yet");
            return;
        }
        System.out.println("You have habits:");
        System.out.printf("%-10s%-20s%-20s%s%n", "index", "name", "start at:", "description");
        for (Habit habit : habitList) {
            System.out.printf("%-10d%-20s%-20s%s%n", habit.getId(), habit.getName(),
                    habit.getDateStart().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), habit.getDescription());
        }
    }

    public static void sayHabitExists() {
        System.out.println("The habit with this name is already exists. Try again.");
    }

    public static void sayHabitCreates() {
        System.out.println("Success! The habit was added.");
    }

    public static void sayHabitUpdate() {
        System.out.println("Success! The changes have been saved.");
    }
}
