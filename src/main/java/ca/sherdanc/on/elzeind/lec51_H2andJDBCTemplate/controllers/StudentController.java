package ca.sherdanc.on.elzeind.lec51_H2andJDBCTemplate.controllers;

import ca.sherdanc.on.elzeind.lec51_H2andJDBCTemplate.beans.Student;
import ca.sherdanc.on.elzeind.lec51_H2andJDBCTemplate.databases.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class StudentController {
    Student student = new Student();
    @Autowired
    private DatabaseAccess da;
    List<Student> studentList = new CopyOnWriteArrayList<>();
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("studentList", studentList);
        return "index";
    }
    @PostMapping("/addStudent")
    public String addGame(Model model, @ModelAttribute Student student) {
        studentList.add(student);
        da.insertStudent(student);
        model.addAttribute("student", new Student());
        model.addAttribute("studentList", studentList);
        return "index";
    }
}

