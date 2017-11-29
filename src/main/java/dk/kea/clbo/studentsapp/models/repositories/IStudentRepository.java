package dk.kea.clbo.studentsapp.models.repositories;

import dk.kea.clbo.studentsapp.models.entities.Student;

import java.util.List;

public interface IStudentRepository {

    // CRUD operations
    public void create(Student student);

    public Student read(int id);

    public List<Student> readAll();

    public boolean update(Student student);

    public boolean delete(Student student);
}
