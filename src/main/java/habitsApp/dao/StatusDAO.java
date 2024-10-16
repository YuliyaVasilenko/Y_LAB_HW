package habitsApp.dao;

import habitsApp.habit.Habit;
import habitsApp.menu.HabitApp;
import habitsApp.status.Status;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author YuliyaVasilenko
 * @date 16-10-2024
 * Description: this class describes the interaction between a status of habit's entity and a database
 */
public class StatusDAO extends EntityDAO<Status> {
    /**
     * @ Method Name: find
     * @ Description: search for a status entity in database by a unique field 'id'
     * @ param      : [int]
     * @ return     : habitsApp.status.Status
     */
    @Override
    public Status find(int id) {
        Status status = null;
        if (HabitApp.dataBaseStatusOfHabits.containsKey(id)) {
            for (Integer key : HabitApp.dataBaseStatusOfHabits.keySet()) {
                if (key == id) {
                    status = HabitApp.dataBaseStatusOfHabits.get(id);
                    break;
                }
            }
        }
        return status;
    }

    /**
     * @ Method Name: find
     * @ Description: search for a status entity in database by a field 'date', which is unique for every habit
     * @ param      : [int, java.time.LocalDate]
     * @ return     : habitsApp.status.Status
     */
    public Status find(int habitId, LocalDate date) {
        Status status = null;
        for (Integer id : HabitApp.dataBaseStatusOfHabits.keySet()) {
            status = HabitApp.dataBaseStatusOfHabits.get(id);
            if (status.getDate().equals(date)) {
                break;
            }
        }
        return status;
    }

    /**
     * @ Method Name: create
     * @ Description: creating a new status in the database (for certain habit)
     * @ param      : [habitsApp.status.Status]
     * @ return     : boolean
     */
    @Override
    public boolean create(Status status) {
        HabitApp.dataBaseStatusOfHabits.put(status.getId(), status);
        return true;
    }

    /**
     * @ Method Name: update
     * @ Description: updating status data in the database
     * @ param      : [habitsApp.status.Status]
     * @ return     : habitsApp.status.Status
     */
    @Override
    public Status update(Status status) {
        HabitApp.dataBaseStatusOfHabits.put(status.getId(), status);
        status = new StatusDAO().find(status.getId());
        return status;
    }

    /**
     * @ Method Name: delete
     * @ Description: deleting a status from the database
     * @ param      : [habitsApp.status.Status]
     * @ return     : boolean
     */
    @Override
    public boolean delete(Status status) {
        HabitApp.dataBaseStatusOfHabits.remove(status.getId());
        return true;
    }

    public List<Status> findAllStatusesOfHabits(int habitId) {
        List<Status> list = new ArrayList<>();
        for (Integer id : HabitApp.dataBaseStatusOfHabits.keySet()) {
            if (HabitApp.dataBaseStatusOfHabits.get(id).getHabitId() == habitId){
                list.add(HabitApp.dataBaseStatusOfHabits.get(id));
            }
        }
        return list;
    }

    /**
     * @ Method Name: filterStatusByDate
     * @ Description: search for all statuses of habit in database whose start date is after a certain date
     * @ param      : [int, java.time.LocalDate]
     * @ return     : java.util.Deque<habitsApp.status.Status>
     */
    public Deque<Status> filterStatusByDate(int habitId, LocalDate startDate) {
        Deque<Status> list = new ArrayDeque<>();
        for (Integer id : HabitApp.dataBaseStatusOfHabits.keySet()) {
            Status status = HabitApp.dataBaseStatusOfHabits.get(id);
            if (status.getHabitId() == habitId && status.getDate().isAfter(startDate)) {
                list.offer(status);
            }
        }
        return list;
    }

    /**
     * @ Method Name: filterByStatus
     * @ Description: search for all statuses of habit in database that have a certain status
     * @ param      : [int, boolean]
     * @ return     : habitsApp.status.Status
     */
    public Status filterByStatus(int habitId, boolean statusSearch) {
        Status statusOnLastDay = null;
        for (Integer key : HabitApp.dataBaseStatusOfHabits.keySet()) {
            statusOnLastDay = HabitApp.dataBaseStatusOfHabits.get(key);
            if (statusOnLastDay.getHabitId() == habitId && statusOnLastDay.isLastModified()
                    && statusOnLastDay.isHasDone() == statusSearch) {
                break;
            }
        }
        return statusOnLastDay;
    }

    /**
     * @ Method Name: searchStatusOnDate
     * @ Description: search for the status of habit in database on a certain date
     * @ param      : [int, java.time.LocalDate]
     * @ return     : habitsApp.status.Status
     */
    public Status searchStatusOnDate(int habitId, LocalDate date) {
        Status statusOnLastDay = null;
        for (Integer key : HabitApp.dataBaseStatusOfHabits.keySet()) {
            statusOnLastDay = HabitApp.dataBaseStatusOfHabits.get(key);
            if (statusOnLastDay.getHabitId() == habitId && statusOnLastDay.getDate().equals(date)) {
                break;
            }
        }
        return statusOnLastDay;
    }
}
