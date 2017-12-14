package dk.kea.clbo.studentsapp.models.repositories;

import dk.kea.clbo.studentsapp.models.entities.Course;
import dk.kea.clbo.studentsapp.models.entities.Enrollment;
import dk.kea.clbo.studentsapp.models.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by clbo on 30/11/2017.
 */
@Repository
public class EnrollmentsRepository {

   @Autowired
   private JdbcTemplate jdbc;
   private SqlRowSet rs;
   private List<Enrollment> enrollments;

    //@Override
    public boolean create(Enrollment enrollment) {
        return false;
    }

    //@Override
    public Enrollment read(int id) {



        return null;
    }

   // @Override
    public List<Enrollment> readAll() {
        rs = jdbc.queryForRowSet("SELECT * FROM students_courses INNER JOIN students ON fk_students = students.students_id INNER JOIN courses ON fk_courses = courses.courses_id WHERE fk_students = 1");
        while(rs.next()){
            enrollments.add(new Enrollment(0,
                    new Student(rs.getInt("students_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getDate("enrollment_date"), rs.getString("cpr")),
                    new Course(rs.getInt("course_id"), rs.getString("title"), rs.getDate("start_date"), rs.getInt("etcs")),
                    rs.getInt("grade")));
        }
        return enrollments;
    }

   // @Override
    public boolean update(Enrollment enrollment) {
        return false;
    }

    // @Override
    public boolean delete(int id) {
        return false;
    }
}
