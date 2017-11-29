package dk.kea.clbo.studentsapp.models.repositories;

import java.util.List;

/**
 * Created by clbo on 24/11/2017.
 */
public interface ICrud<T> {

    // Crud methods

    boolean create(T t);
    T read(int id);
    List<T> readAll();
    boolean update(T t);
    boolean delete(int id);
}
