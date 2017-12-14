package dk.kea.clbo.studentsapp.models.entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by clbo on 24/11/2017.
 */
public class Course {
    private int courseId;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd") // needed for input field on html pages (in order to serve the right format)
    private Date startDate;
    private int etcs;

    private List<Student> enrolled;

    public Course() {}

    public Course(int courseId, String title, Date startDate, int etcs) {
        this.courseId = courseId;
        this.title = title;
        this.startDate = startDate;
        this.etcs = etcs;
    }

    public Course(int courseId, String title, Date startDate, int etcs, List<Student> enrolled) {
        this.courseId = courseId;
        this.title = title;
        this.startDate = startDate;
        this.etcs = etcs;
        this.enrolled = enrolled;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getEtcs() {
        return etcs;
    }

    public void setEtcs(int etcs) {
        this.etcs = etcs;
    }

    public List<Student> getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(List<Student> enrolled) {
        this.enrolled = enrolled;
    }

    @Override
    public String toString() {
        return courseId + " " + title + " " + startDate + " " + etcs;
    }
}
