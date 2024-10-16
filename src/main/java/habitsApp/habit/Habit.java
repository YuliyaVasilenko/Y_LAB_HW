package habitsApp.habit;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author YuliyaVasilenko
 * @date 12-10-2024
 * Description: this class describes a habit's entity
 */
public class Habit implements Serializable {
    private int id;
    private int userID;
    private String name;
    private String description;
    private int period;
    private LocalDate dateStart;
    //private Progress progress;

    static int staticId = 1;

    public Habit() {
    }

    public Habit(int userID, String name, String description, int period) {
        this.userID = userID;
        this.name = name;
        this.description = description;
        this.period = period;
        dateStart = LocalDate.now();
        id = staticId++;
        //progress = new Progress();
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    /*public Progress getProgress() {
        return progress;
    }*/

    @Override
    public String toString() {
        return "The habit " + name + ", start at " + dateStart + ", is repeated on " +
                period + " day, description: " + description;
    }
}
