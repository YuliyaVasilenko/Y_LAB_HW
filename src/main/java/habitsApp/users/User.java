package habitsApp.users;

import habitsApp.habits.Habit;
import habitsApp.habits.HabitManager;
import habitsApp.habits.HabitsSet;
import habitsApp.status.ProgressManager;

import java.io.*;

public class User implements Serializable {
    private int id;
    private String email;
    private String name;
    private String password;
    private HabitsSet habits;

    static int staticId = 1;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        id = staticId++;
        habits = new HabitsSet();
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HabitsSet getHabits() {
        return habits;
    }

    public boolean createHabit() {
        return new HabitManager().createHabit(habits);
    }

    public boolean updateHabit() {
        return new HabitManager().updateHabit(habits);
    }

    public boolean deleteHabit() {
        return new HabitManager().deleteHabit(habits);
    }

    public void showAndSortAllHabits() {
        new HabitManager().showAndSortAllHabits(habits);
    }

    public boolean setStatus() {
        Habit habit = new HabitManager().choseHabit(habits);
        return new ProgressManager().setStatus(habit);
    }

    public void getStatisticsFromPeriod() {
        Habit habit = new HabitManager().choseHabit(habits);
        new ProgressManager().getStatistics(habit);
    }

}

