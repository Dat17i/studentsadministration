package dk.kea.clbo.studentsapp.controllers;

import dk.kea.clbo.studentsapp.models.entities.Course;
import dk.kea.clbo.studentsapp.models.entities.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CoursesController {

    private List<Course> courses = new ArrayList<>();

    @GetMapping("/courses")
    public String index(Model model){
        model.addAttribute("courses", courses);
        return "/courses/index";
    }


    @GetMapping("/courses/create")
    public String create(Model model) {
        model.addAttribute("course", new Course());
        return "/courses/create";
    }

    @PostMapping("/courses/create")
    public String create(@ModelAttribute Course course) {

        courses.add(course);
        return "redirect:/courses";
    }
}
