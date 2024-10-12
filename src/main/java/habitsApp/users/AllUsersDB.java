package habitsApp.users;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AllUsersDB implements Serializable{
    private Map<String, User> db = new HashMap<>();

    public Map<String, User> getdb() {
        return db;
    }

    public void setDb(Map<String, User> db) {
        this.db = db;
    }

    public void serialization() {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("db.ser"))) {
            os.writeObject(this);
        } catch (IOException e) {
            System.out.println("Exception, " + e.getMessage());
        }
    }

    public static AllUsersDB deserialization() {
        AllUsersDB dbDeserialize;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("db.ser"))) {
            dbDeserialize = (AllUsersDB) ois.readObject();
            return dbDeserialize;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception, " + e.getMessage());
        }
        return null;
    }
}
