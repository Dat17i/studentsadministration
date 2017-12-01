package dk.kea.clbo.studentsapp.models.entities.ViewModels;

import dk.kea.clbo.studentsapp.models.entities.Course;
import dk.kea.clbo.studentsapp.models.entities.Enrollment;
import dk.kea.clbo.studentsapp.models.entities.Student;

import java.util.List;

/**
 * Created by clbo on 01/12/2017.
 */
public class StudentsViewModel {

    private Student student;
    private List<Course> courses;
    private List<Enrollment> enrollments;

    public StudentsViewModel() {
    }

    public StudentsViewModel(Student student) {
        this.student = student;
    }

    public StudentsViewModel(Student student, List<Course> courses, List<Enrollment> enrollments) {
        this.student = student;
        this.courses = courses;
        this.enrollments = enrollments;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
