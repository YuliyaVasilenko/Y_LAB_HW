package habitsApp.users;

import habitsApp.habit.Habit;
import habitsApp.habit.HabitManager;
import habitsApp.status.StatusManager;

import java.io.Serializable;

/**
 * @author YuliyaVasilenko
 * @date 12-10-2024
 * Description: this class describes a user's entity
 */
public class User implements Serializable {
    private int id;
    private String email;
    private String name;
    private String password;
    //private HabitDAO habit;

    static int staticId = 1;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        id = staticId++;
        //habit = new HabitDAO();
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

    /*public HabitDAO getHabits() {
        return habit;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getId() == user.getId();
    }

    public boolean createHabit() {
        return new HabitManager().createHabit(id);
    }

    public boolean updateHabit() {
        return new HabitManager().updateHabit(id);
    }

    public boolean deleteHabit() {
        return new HabitManager().deleteHabit(id);
    }

    public void showAndSortAllHabits() {
        new HabitManager().showAndSortAllHabits(id);
    }

    public boolean setStatus() {
        Habit habit = new HabitManager().choseHabit(id);
        return new StatusManager().setStatus(habit.getId());
    }

    /*public void getStatisticsFromPeriod() {
        Habit habit = new HabitManager().choseHabit(habits);
        new StatusManager().getStatistics(habit);
    }*/

}

