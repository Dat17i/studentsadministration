package dk.kea.clbo.studentsapp.models.repositories;

import dk.kea.clbo.studentsapp.models.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentInMemory implements IStudentRepository {

    // Repository data (state)
    private List<Student> students = new ArrayList<>();

    @Override
    public void create(Student student) {

        // generate random studentID
        //UUID uuid = UUID.randomUUID();
        //stu.setStudentId(uuid.toString());

        // Generate ID
        int index = readAll().size();
        student.setStudentId(index);
        System.out.println(student.getEnrollmentDate());
        students.add(student);
    }

    @Override
    public Student read(int id) {

        for (Student stu : students) {

            if (stu.getStudentId() == id) {
                System.out.println(stu);
                return stu;
            }
        }
        return null;
    }

    @Override
    public List<Student> readAll() {
        return students;
    }

    @Override
    public boolean update(Student student) {

        for (Student st : students) {

            if (st.getStudentId() == student.getStudentId()) {
                students.remove(st);
                students.add(1, student);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Student student) {

        boolean st = students.remove(student);
        return st;

    }
}
