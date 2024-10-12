package habitsApp.users.habits;

import habitsApp.users.habits.status.Execution;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Habit implements Serializable {
    private String name;
    private String description;
    private int period;
    private LocalDate dateStart;
    private ArrayList<Execution> statusDB;

    public Habit() {
    }

    public Habit(String name, String description, int period) {
        this.name = name;
        this.description = description;
        this.period = period;
        dateStart = LocalDate.now();
        statusDB = new ArrayList<>();
        statusDB.add(new Execution(dateStart));
    }

    {
        if (!statusDB.contains(new Execution(LocalDate.now()))) {
            statusDB.add(new Execution(LocalDate.now()));
        }
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

    public ArrayList<Execution> getStatusDB() {
        return statusDB;
    }

    public void setStatusDB(ArrayList<Execution> statistics) {

        this.statusDB = statistics;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("The habit ").append(name).append(", start at ").append(dateStart).append(", is repeated on ")
                .append(period).append(" day, description: ").append(description);
        return sb.toString();
    }
}
