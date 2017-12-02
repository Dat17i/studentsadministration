package dk.kea.clbo.studentsapp.models.repositories;

import dk.kea.clbo.studentsapp.models.entities.Course;
import dk.kea.clbo.studentsapp.models.util.DateHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by clbo on 30/11/2017.
 */
@Repository
public class CorsesRepository implements ICrud<Course> {

    private final JdbcTemplate jdbc;

    @Autowired
    public CorsesRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public boolean create(Course course) {

        // Converting dates into the right format
        String startDate = DateHandling.convertDate(course.getStartDate());

        jdbc.update("insert into courses(title, start_date, etcs) values('" + course.getTitle() + "','" + startDate + "', '" + course.getEtcs() + "')");
        return true;
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
