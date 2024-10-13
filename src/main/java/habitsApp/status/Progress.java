package habitsApp.status;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Deque;

public class Progress {
    private Deque<Status> execution = new ArrayDeque<>();

    public Deque<Status> getExecution() {
        return execution;
    }

    public void createExecution(LocalDate date, boolean hasDone) { //add progress
        execution.addFirst(new Status(date, hasDone));
    }

    public Deque<Status> filterByDate(LocalDate startDate) {
        Deque<Status> list = new ArrayDeque<>();
        for (Status status : execution) {
            if (status.getDate().isAfter(startDate)) {
                list.add(status);
            }
        }
        return list;
    }

    public Status filterByStatus(boolean statusSearch) {
        Status statusOnLastDay = execution.peek();
        if (statusOnLastDay.isHasDone() == statusSearch) {
            return statusOnLastDay;
        }
        return null;
    }

    public Status searchStatusOnDate(LocalDate date) {
        for (Status status : execution) {
            if (status.getDate().equals(date)) {
                return status;
            }
        }
        return null;
    }
}
