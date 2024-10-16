package habitsApp.database;

import habitsApp.habit.Habit;
import habitsApp.status.Status;
import habitsApp.users.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YuliyaVasilenko
 * @date 12-10-2024
 * Description: this class saves all the databases of the project, which are represented as a maps (HashMap)
 */
public class  MapDataBase implements Serializable{
    private Map<Integer, User> dbUsers = new HashMap<>();
    private Map<Integer, User> dbBlockedUsers = new HashMap<>();
    private Map<Integer, Habit> dbHabits = new HashMap<>();
    private Map<Integer, Status> dbStatus = new HashMap<>();

    public Map<Integer, User> getDbUsers() {
        return dbUsers;
    }

    public void setDbUsers(Map<Integer, User> dbUsers) {
        this.dbUsers = dbUsers;
    }

    public Map<Integer, User> getDbBlockedUsers() {
        return dbBlockedUsers;
    }

    public void setDbBlockedUsers(Map<Integer, User> dbBlockedUsers) {
        this.dbBlockedUsers = dbBlockedUsers;
    }

    public Map<Integer, Habit> getDbHabits() {
        return dbHabits;
    }

    public void setDbHabits(Map<Integer, Habit> dbHabits) {
        this.dbHabits = dbHabits;
    }

    public Map<Integer, Status> getDbStatus() {
        return dbStatus;
    }

    public void setDbStatus(Map<Integer, Status> dbStatus) {
        this.dbStatus = dbStatus;
    }

    /**
     * @ Method Name: serialization
     * @ Description: serializing database = save
     * @ param      : [java.lang.String]
     * @ return     : void
     */
    public void serialization(String nameDB) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(nameDB + ".ser"))) {
            os.writeObject(this);
        } catch (IOException e) {
            System.out.println("Exception, " + e.getMessage());
        }
    }

    /**
     * @ Method Name: deserialization
     * @ Description: deserializing database = get
     * @ param      : [java.lang.String]
     * @ return     : habitsApp.database.MapDataBase
     */
    public static MapDataBase deserialization(String nameDB) {
        MapDataBase dbDeserialize;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nameDB + ".ser"))) {
            dbDeserialize = (MapDataBase) ois.readObject();
            return dbDeserialize;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception, " + e.getMessage());
        }
        return null;
    }
}
