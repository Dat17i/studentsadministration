package dk.kea.clbo.studentsapp.models.repositories;

import dk.kea.clbo.studentsapp.models.entities.Course;
import dk.kea.clbo.studentsapp.models.entities.Enrollment;
import dk.kea.clbo.studentsapp.models.entities.Student;
import dk.kea.clbo.studentsapp.models.util.DateHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clbo on 30/11/2017.
 */
@Repository
public class CorsesRepository implements ICrud<Course> {

    private final JdbcTemplate jdbc;
    private List<Course> courses = new ArrayList<>();
    private SqlRowSet rs;

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


    /**
     *
     * @param id
     * @return A Student with a list of enrolled course and a grade
     */
    @Override
    public List<Enrollment> readOneWithEnrollments(int id) {

        List<Enrollment> enrollments = new ArrayList<>();

        rs = jdbc.queryForRowSet("SELECT * FROM courses " +
                "LEFT JOIN students_courses ON fk_courses = courses.courses_id " +
                "LEFT JOIN students ON fk_students = students.students_id WHERE courses_id =" + id);

        while(rs.next()){

            enrollments.add(new Enrollment(0, new Student(rs.getInt("students_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("enrollment_date"),
                    rs.getString("cpr")),
                    new Course(rs.getInt("courses_id"),
                            rs.getString("title"),
                            rs.getDate("start_date"),
                            rs.getInt("etcs")), rs.getInt("grade")));
        }

        return enrollments;

    }

    @Override
    public Course read(int id) {


        rs = jdbc.queryForRowSet("select * from courses WHERE courses_id ="+ id);
        if (rs.next()) {

            return new Course(rs.getInt("courses_id"),
                    rs.getString("title"),
                    rs.getDate("start_date"),
                    rs.getInt("etcs"));

        }
        return new Course();
    }

    @Override
    public List<Course> readAll() {
        courses.clear();
        rs = jdbc.queryForRowSet("select * from courses");
        while (rs.next()) {

            courses.add(new Course(rs.getInt("courses_id"),
                    rs.getString("title"),
                    rs.getDate("start_date"),
                    rs.getInt("etcs")));

        }
        return courses;
    }

    @Override
    public boolean update(Course course) {

        // Converting dates into the right format
        String startDate = DateHandling.convertDate(course.getStartDate());


        System.out.println(course);
        int result = jdbc.update("UPDATE courses SET " +
                "title ='" + course.getTitle() + "' , " +
                "start_date='" + startDate + "' ," +
                "etcs='" + course.getEtcs() +
                "' WHERE courses_id = '" + course.getCourseId() + "'");
        return true;
    }

    @Override
    public boolean delete(int id) {
        int result = jdbc.update("DELETE FROM courses WHERE courses_id='" + id + "'");
        return true;

    }
}
