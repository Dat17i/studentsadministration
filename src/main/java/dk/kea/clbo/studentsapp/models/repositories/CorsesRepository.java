package dk.kea.clbo.studentsapp.models.repositories;

import dk.kea.clbo.studentsapp.models.entities.Course;

import java.util.List;

/**
 * Created by clbo on 30/11/2017.
 */
public class CorsesRepository implements ICrud<Course> {
    @Override
    public boolean create(Course course) {
        return false;
    }

    @Override
    public Course read(int id) {
        return null;
    }

    @Override
    public List<Course> readAll() {
        return null;
    }

    @Override
    public boolean update(Course course) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
