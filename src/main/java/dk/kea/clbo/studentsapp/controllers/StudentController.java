package dk.kea.clbo.studentsapp.controllers;

import dk.kea.clbo.studentsapp.models.entities.Student;
import dk.kea.clbo.studentsapp.models.repositories.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController  {

    private final IStudentRepository studentRepository;

    @Autowired
    public StudentController(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

   /* public StudentController() {
        studentRepository = new StudentInMemory(); //StudentInMemory();
    } */

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("std", studentRepository.readAll());
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("student", new Student());
        return "create";

    }

    @PostMapping("/create")
    public String create(@ModelAttribute Student stu) {

        studentRepository.create(stu);
        return "redirect:/";
    }

    @GetMapping("/details")
    public String details(@RequestParam("id") int id, Model model) {
        model.addAttribute("stu", studentRepository.readOneWithEnrollments(id));
        return "details";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id, Model model) {
        model.addAttribute("stu", studentRepository.read(id));

        return "delete";
    }

    @GetMapping("/edit")
    public String update(@RequestParam("id") int id, Model model){
        model.addAttribute("student", studentRepository.read(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute Student student) {
        studentRepository.update(student);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Student stu, Model model){
        studentRepository.delete(stu.getStudentId());
        return "redirect:/";
    }

    @GetMapping("/enroll")
    public String enroll(@RequestParam("id") String id, Model model) {
        // TODO: Get list of Courses
        // model.addAttribute("courses", courserepository.readAll());
        model.addAttribute("stu", studentRepository.read(Integer.parseInt(id)));
        return "enroll"; // enroll.html is similar to create student
    }

}
