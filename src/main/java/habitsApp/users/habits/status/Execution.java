package habitsApp.users.habits.status;

import java.time.LocalDate;

public class Execution {
    private LocalDate date;
    private boolean hasDone;

    public Execution(LocalDate date) {
        this.date = date;
        hasDone = false;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isHasDone() {
        return hasDone;
    }

    public void setHasDone(boolean hasDone) {
        this.hasDone = hasDone;
    }
}
