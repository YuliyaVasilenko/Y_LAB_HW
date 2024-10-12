package habitsApp.users.habits.status;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class StatusDB {
    private Map<LocalDate, Boolean> status = new HashMap<>();

    public Map<LocalDate, Boolean> getStatus() {
        return status;
    }

    public void setStatus(Map<LocalDate, Boolean> status) {
        this.status = status;
    }
}
