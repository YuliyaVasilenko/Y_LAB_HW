package habitsApp.dao;

import habitsApp.habit.Habit;
import habitsApp.menu.HabitApp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YuliyaVasilenko
 * @date 12-10-2024
 * Description: this class describes the interaction between a habit's entity and a database
 */
public class HabitDAO extends EntityDAO<Habit> {
    /**
     * @ Method Name: find
     * @ Description: search for a habit entity in database by a unique field 'id'
     * @ param      : [int]
     * @ return     : habitsApp.habit.Habit
     */
    @Override
    public Habit find(int id) {
        Habit habit = null;
        if (HabitApp.dataBaseHabits.containsKey(id)) {
            for (Integer key : HabitApp.dataBaseHabits.keySet()) {
                if (key == id) {
                    habit = HabitApp.dataBaseHabits.get(id);
                    break;
                }
            }
        }
        return habit;
    }

    /**
     * @ Method Name: find
     * @ Description: search for a habit entity in database by a field 'name', which is unique for every user
     * @ param      : [java.lang.String]
     * @ return     : habitsApp.habit.Habit
     */
    public Habit find(int userId, String name) {
        Habit habit = null;
        for (Integer id : HabitApp.dataBaseHabits.keySet()) {
            habit = HabitApp.dataBaseHabits.get(id);
            if (habit.getUserID() == userId && habit.getName().equals(name)) {
                break;
            }
            else {
                habit = null;
            }
        }
        return habit;
    }

    /**
     * @ Method Name: create
     * @ Description: creating a new habit in the database (for certain user)
     * @ param      : [habitsApp.habit.Habit]
     * @ return     : boolean
     */
    @Override
    public boolean create(Habit habit) {
        HabitApp.dataBaseHabits.put(habit.getId(), habit);
        return true;
    }

    /**
     * @ Method Name: update
     * @ Description: updating habit data in the database
     * @ param      : [habitsApp.habit.Habit]
     * @ return     : habitsApp.habit.Habit
     */
    @Override
    public Habit update(Habit habit) {
        HabitApp.dataBaseHabits.put(habit.getId(), habit);
        habit = new HabitDAO().find(habit.getId());
        return habit;
    }

    /**
     * @ Method Name: delete
     * @ Description: deleting a habit from the database
     * @ param      : [habitsApp.habit.Habit]
     * @ return     : boolean
     */
    @Override
    public boolean delete(Habit habit) {
        HabitApp.dataBaseHabits.remove(habit.getId());
        return true;
    }

    /**
     * @ Method Name: findAllHabits
     * @ Description: search for all habits entity in database for some user
     * @ param      : [int]
     * @ return     : java.util.List<habitsApp.habit.Habit>
     */
    public List<Habit> findAllHabits(int userId) {
        List<Habit> list = new ArrayList<>();
        for (Integer id : HabitApp.dataBaseHabits.keySet()) {
            if (HabitApp.dataBaseHabits.get(id).getUserID() == userId){
                list.add(HabitApp.dataBaseHabits.get(id));
            }
        }
        return list;
    }

    /**
     * @ Method Name: filterHabitsByDate
     * @ Description: search for all habits entity in database whose start date is after a certain date
     * @ param      : [int, java.time.LocalDate]
     * @ return     : java.util.List<habitsApp.habit.Habit>
     */
    public List<Habit> filterHabitsByDate(int userId, LocalDate date) {
        List<Habit> list = new ArrayList<>();
        for (Integer id : HabitApp.dataBaseHabits.keySet()) {
            Habit habit = HabitApp.dataBaseHabits.get(id);
            if (habit.getUserID() == userId && habit.getDateStart().isAfter(date)) {
                    list.add(HabitApp.dataBaseHabits.get(id));
            }
        }
        return list;
    }

    /**
     * @ Method Name: filterByStatus
     * @ Description: search for all habits entity in database that have a certain status
     * @ param      : [int, boolean]
     * @ return     : java.util.List<habitsApp.habit.Habit>
     */
    /*public List<Habit> filterByStatus(int userId, boolean status) {
        List<Habit> list = new ArrayList<>();
        for (Integer id : HabitApp.dataBaseHabits.keySet()) {
            Habit habit = HabitApp.dataBaseHabits.get(id);
            if (habit.getUserID() == userId && habit.getProgress().filterByStatus(status) != null) {
                list.add(HabitApp.dataBaseHabits.get(id));
            }
        }
        return list;
    }*/
}
