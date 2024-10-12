package habitsApp.users.habits;

import java.io.Serializable;
import java.util.ArrayList;

public class HabitsDB implements Serializable {
    private ArrayList<Habit> list = new ArrayList<>();

    public ArrayList<Habit> getList() {
        return list;
    }

    public void setList(ArrayList<Habit> habits) {
        this.list = habits;
    }
}
