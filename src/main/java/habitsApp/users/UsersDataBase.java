package habitsApp.users;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UsersDataBase implements Serializable{
    private Map<Integer, User> db = new HashMap<>();

    public Map<Integer, User> getdb() {
        return db;
    }

    public void setDb(Map<Integer, User> db) {
        this.db = db;
    }

    public void serialization(String nameDB) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(nameDB + ".ser"))) {
            os.writeObject(this);
        } catch (IOException e) {
            System.out.println("Exception, " + e.getMessage());
        }
    }

    public static UsersDataBase deserialization(String nameDB) {
        UsersDataBase dbDeserialize;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nameDB + ".ser"))) {
            dbDeserialize = (UsersDataBase) ois.readObject();
            return dbDeserialize;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception, " + e.getMessage());
        }
        return null;
    }
}
