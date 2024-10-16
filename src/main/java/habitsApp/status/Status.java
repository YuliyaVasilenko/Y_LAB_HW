package habitsApp.status;

import java.time.LocalDate;

/**
 * @author YuliyaVasilenko
 * @date 12-10-2024
 * Description: this class describes a status of the habit entity
 */
public class Status {
    private int id;
    private int habitId;
    private LocalDate date;
    private boolean hasDone;
    private boolean lastModified;

    static int staticId = 1;

    public Status(int habitId, LocalDate date, boolean hasDone) {
        this.habitId = habitId;
        this.date = date;
        this.hasDone = hasDone;
        lastModified = true;
        id = staticId++;
    }

    /*public Status(LocalDate date, boolean hasDone) {
        this.date = date;
        this.hasDone = hasDone;
    }*/

    public int getId() {
        return id;
    }

    public int getHabitId() {
        return habitId;
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

    public boolean isLastModified() {
        return lastModified;
    }
}
