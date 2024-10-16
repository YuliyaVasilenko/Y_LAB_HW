package habitsApp.database;

import habitsApp.menu.HabitApp;

/**
 * @author YuliyaVasilenko
 * @date 16-10-2024
 * Description: this class gets and saves all the databases of the project in MapDataBase object
 */
public class DataBaseManager {
    /**
     * @ Method Name: getDataBases
     * @ Description: getting and setting all databases
     * @ param      : [java.lang.String]
     * @ return     : void
     */
    public void getDataBases(String nameDB) {
        MapDataBase dataBaseSaved = MapDataBase.deserialization(nameDB);
        if (dataBaseSaved == null) {
            dataBaseSaved = new MapDataBase();
        }
        HabitApp.dataBaseUsers = dataBaseSaved.getDbUsers();
        HabitApp.dataBaseBlockedUsers = dataBaseSaved.getDbBlockedUsers();
        HabitApp.dataBaseHabits = dataBaseSaved.getDbHabits();
        HabitApp.dataBaseStatusOfHabits = dataBaseSaved.getDbStatus();
    }

    /**
     * @ Method Name: saveDataBases
     * @ Description: saving all databases
     * @ param      : [java.lang.String]
     * @ return     : void
     */
    public void saveDataBases(String nameDB) {
        MapDataBase dataBasesToSave = new MapDataBase();
        dataBasesToSave.setDbUsers(HabitApp.dataBaseUsers);
        dataBasesToSave.setDbBlockedUsers(HabitApp.dataBaseBlockedUsers);
        dataBasesToSave.setDbHabits(HabitApp.dataBaseHabits);
        dataBasesToSave.setDbStatus(HabitApp.dataBaseStatusOfHabits);
        dataBasesToSave.serialization(nameDB);
    }
}
