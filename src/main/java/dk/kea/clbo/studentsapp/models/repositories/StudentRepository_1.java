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
 * Created by clbo on 20/11/2017.
 */
@Repository
public class StudentRepository_1 implements IStudentRepository {

    @Autowired
    private JdbcTemplate jdbc;
    private ArrayList<Student> students;
    private SqlRowSet rs;

    public StudentRepository_1() {
        students = new ArrayList<Student>();
    }


    @Override
    public boolean create(Student student) {

        // Converting dates into the right format
        String enrollmentdate = DateHandling.convertDate(student.getEnrollmentDate());

        jdbc.update("insert into students(first_name,last_name, enrollment_date, cpr)" +
                "values('" + student.getFirstName() + "','" + student.getLastName() + "', '" +
                enrollmentdate + "', '" + student.getCpr() + "')");

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


        rs = jdbc.queryForRowSet("SELECT * FROM students " +
                "LEFT JOIN students_courses ON fk_students = students.students_id " +
                "LEFT JOIN courses ON fk_courses = courses.courses_id WHERE students_id =" + id);

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
    public Student read(int id){
        // Student without enrollments
        rs = jdbc.queryForRowSet("SELECT * FROM students where students_id ='" + id + "'");
        while (rs.next()) {

            return  new Student(rs.getInt("students_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("enrollment_date"),
                    rs.getString("cpr"));

        }

        return new Student();
    }

    @Override
    public List<Student> readAll() {
        students.clear();
        rs = jdbc.queryForRowSet("select * from students");
        while (rs.next()) {

            students.add(new Student(rs.getInt("students_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("enrollment_date"),
                    rs.getString("cpr")));

        }
        return students;
    }

    @Override
    public boolean update(Student student) {

        int result = jdbc.update("UPDATE students SET " +
                "first_name ='" + student.getFirstName() + "' , " +
                "last_name='" + student.getLastName() + "' ," +
                "enrollment_date='" + student.getEnrollmentDate() + "' ," +
                "cpr='" + student.getCpr() + "' WHERE students_id = '" + student.getStudentId() + "'");
        return true;

    }

    @Override
    public boolean delete(int id) {
        // TODO: return type bool
        int result = jdbc.update("DELETE FROM students WHERE students_id='" + id + "'");
        return true;
    }
}
