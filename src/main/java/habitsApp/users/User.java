package habitsApp.users;

import habitsApp.CheckWriting;
import habitsApp.users.habits.Habit;
import habitsApp.users.habits.HabitsDB;
import habitsApp.users.habits.HabitsManagerDAO;
import habitsApp.users.habits.status.ExecutionDAO;

import java.io.*;

public class User implements Serializable {
    private String email;
    private String name;
    private String password;
    private HabitsDB habits;

    public User() {
    }

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        habits = new HabitsDB();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HabitsDB getHabits() {
        return habits;
    }

    public void setHabits(HabitsDB habits) {
        this.habits = habits;
    }

    public static User doAuthorizationOrRegistration() {
        String email = CheckWriting.checkEmail();
        if (email == null) {
            return null;
        }
        User user = new UserDAO().find(email);
        if (user != null) { //authorization
            String password = CheckWriting.checkPassword(user.getPassword());
            if (password == null) {
                return null;
            }
            System.out.println("Authentication success. Hello " + user.getName() + "!");
        } else { //registration
            System.out.println("User has not been found, would you like to register? Press 1 to register, press 0 to return to the menu");
            int choice = CheckWriting.isRightOperation(1, 1, 0);
            if (choice == 1) {
                String password = CheckWriting.checkPassword();
                String name = CheckWriting.checkWord("your name");
                if (password != null && name != null) {
                    UserDAO userDAO = new UserDAO();
                    if (userDAO.createUser(new User(email, name, password))) {
                        user = userDAO.find(email);
                        System.out.println("Success! Hello " + user.getName() + "!");
                    }
                }
            }
        }
        return user;
    }

    public boolean changeData() {
        System.out.println("If you want to change your name press 1, to change your email press 2, " +
                "to change your password press 3, to delete your account press 4, to exit press 0");
        int choice = CheckWriting.isRightOperation(1, 3, 0);
        String newData;
        boolean hasChanged = true;
        switch (choice) {
            case 1 -> {
                newData = CheckWriting.checkWord("your name");
                if (newData != null) {
                    setName(newData);
                }
            }
            case 2 -> {
                newData = CheckWriting.checkEmail();
                if (newData != null) {
                    setEmail(newData);
                }
            }
            case 3 -> {
                newData = CheckWriting.checkPassword();
                if (newData != null) {
                    setPassword(newData);
                }
            }
            default -> hasChanged = false;
        }
        if (hasChanged) {
            new UserDAO().update(this);
            System.out.println("Success! The changes have been saved.\n");
        }
        return hasChanged;
    }

    public boolean deleteUser() {
        System.out.println("Are you sure you want to delete your account? All information will be lost. " +
                "To permanently delete your account press 1, to cancel press 0");
        int confChoice = CheckWriting.isRightOperation(1, 1, 0);
        if (confChoice == 1) {
            new UserDAO().deleteUser(this);
            return true;
        }
        return false;
    }

    public static boolean isAuthorized(User user) {
        if (user == null) {
            System.out.println("You are not logged in, log in or register first");
            return false;
        }
        return true;
    }

    public boolean createHabit() {
        String habitName = CheckWriting.checkWord("habit's name");
        if (habitName == null) return false;
        HabitsManagerDAO hmDAO = new HabitsManagerDAO();
        if (hmDAO.findHabit(habitName) != null) {
            System.out.println("The habit with this name is already exists. Try again.");
            return false;
        }
        String habitDescription = CheckWriting.checkWord("habit's description");
        if (habitDescription == null) return false;
        System.out.println("Write the number of days during which this habit is repeated from 1 day to 30 days");
        int habitPeriod = CheckWriting.isRightOperation(1, 30, 0);
        if (habitPeriod == 0) return false;
        Habit habit = new Habit(habitName, habitDescription, habitPeriod);
        hmDAO.create(habit);
        System.out.println("Success! The habit was added.");
        if (habit != null) {
            habits.getList().add(habit);
            return true;
        }
        return false;
    }

    public boolean updateHabit() {
        int index = new HabitsManagerDAO().choseHabitIndex();
        if (index == -1) return false;
        System.out.println("If you want to edit the habit's name press 1, to edit the habit's description press 2, " +
                "to edit the habit's number of days during which this habit is repeated press 3, to exit to menu press 0");
        int choice = CheckWriting.isRightOperation(1, 3, 0);
        String newData;
        if (choice == 3) {
            int newPeriod = CheckWriting.isRightOperation(1, 30, 0);
            newData = Integer.toString(newPeriod);
        } else {
            newData = CheckWriting.checkWord("new data");
        }
        Habit habitUpdated = new HabitsManagerDAO().update(index, newData, choice);
        if (habitUpdated != null) {
            habits.getList().set(0, habitUpdated);
            return true;
        }
        return false;
    }

    public boolean deleteHabit() {
        int index = new HabitsManagerDAO().choseHabitIndex();
        if (index == -1) return false;
        System.out.println("Are you sure you want to delete the habit? To confirm press 1, to exit to menu press 0");
        int choice = CheckWriting.isRightOperation(1, 1, 0);
        Habit habitDeleted = new HabitsManagerDAO().delete(index-1);
        if (habitDeleted != null) {
            habits.getList().remove(habitDeleted);
            return true;
        }
        return false;
    }

    public void showAndSortAllHabits() {
        HabitsManagerDAO hmDAO = new HabitsManagerDAO();
        hmDAO.showAndSortAllHabits();
    }

    public void setStatus() {
        HabitsManagerDAO hmDAO = new HabitsManagerDAO();
        int index = hmDAO.choseHabitIndex();
        if (index == -1) return;
        System.out.println("To set status 'done' press 1, to set status 'unfinished' press 2, to exit to menu press 0");
        int choice = CheckWriting.isRightOperation(1, 2,0);
        if (choice==0) return;
        boolean bool = choice==1 ? true : false;
        ExecutionDAO ex = new ExecutionDAO();
        ex.setStatus(bool);
    }
}

