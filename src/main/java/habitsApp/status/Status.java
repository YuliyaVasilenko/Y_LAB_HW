package habitsApp.status;

import java.time.LocalDate;

public class Status {
    private LocalDate date;
    private boolean hasDone;

    public Status(LocalDate date, boolean hasDone) {
        this.date = date;
        this.hasDone = hasDone;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isHasDone() {
        return hasDone;
    }

    public void setHasDone(boolean hasDone) {
        this.hasDone = hasDone;
    }
}
