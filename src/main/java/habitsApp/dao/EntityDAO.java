package habitsApp.dao;
/**
 * @author YuliyaVasilenko
 * @date 12-10-2024
 * Description: this class provides methods that are required for the interaction between some entity and a database
 */
public abstract class EntityDAO<T> {
    //public abstract T find(String fieldName);

    public abstract T find(int id);

    public abstract boolean create(T object);

    public abstract T update(T object);

    public abstract boolean delete(T object);
}
