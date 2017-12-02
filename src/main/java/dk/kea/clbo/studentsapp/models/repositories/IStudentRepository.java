package dk.kea.clbo.studentsapp.models.repositories;

import dk.kea.clbo.studentsapp.models.entities.Enrollment;
import dk.kea.clbo.studentsapp.models.entities.Student;
import java.util.List;

public interface IStudentRepository {

    // CRUD operations
    public boolean create(Student student);

    public List<Enrollment> readOneWithEnrollments(int id);

    public Student read(int id);

    public List<Student> readAll();

    public boolean update(Student student);

    public boolean delete(int id);
}
