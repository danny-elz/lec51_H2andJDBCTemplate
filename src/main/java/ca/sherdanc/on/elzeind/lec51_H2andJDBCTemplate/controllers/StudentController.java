package ca.sherdanc.on.elzeind.lec51_H2andJDBCTemplate.controllers;
import ca.sherdanc.on.elzeind.lec51_H2andJDBCTemplate.beans.Student;
import ca.sherdanc.on.elzeind.lec51_H2andJDBCTemplate.databases.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
    @Controller
public class StudentController {
    @Autowired
    private DatabaseAccess da;
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("studentList", da.getStudentList());
        return "index";
    }
    @PostMapping("/addStudent")
    public String insertStudent(Model model, @ModelAttribute Student student) {
       // nameList.add(student.getName());
        List<Student> existingStudents = da.getStudentListById(student.getId());
        if (existingStudents.isEmpty()) {
            // If the student doesn't exist (based on ID), insert a new student
            da.insertStudent(student);
        } else {
            // If the student exists, update the existing student
            da.updateStudent(student);
        }
        model.addAttribute("student", new Student());
        model.addAttribute("studentList", da.getStudentList());
        System.out.println(student);
        return "index";
    }
    @PostMapping ("/filter")
    public String filterStudents(@RequestParam String degreeType, Model model){
        List<Student> filteredStudents = da.getStudentsByDegreeType(degreeType);
        model.addAttribute("student", new Student());
        model.addAttribute("studentList", filteredStudents);
        return "index";
    }
    @PostMapping("/filterByName")
    public String filterByName(@RequestParam String nameInput, Model model) {
        List<Student> filteredStudents = da.getStudentsByName(nameInput);
        model.addAttribute("student", new Student());
        model.addAttribute("studentList", filteredStudents);
        return "index";
    }
    @GetMapping("/deleteStudentById/{id}")
    public String deleteStudentById(Model model, @PathVariable Long id) {
        da.deleteStudentById(id);
        model.addAttribute("student", new Student());
        model.addAttribute("studentList", da.getStudentList());
        return "index";
    }
    @GetMapping("/editStudentById/{id}")
    public String editStudentById(Model model, @PathVariable Long id) {
        Student student = da.getStudentListById(id).get(0);
        da.updateStudent(student);
        model.addAttribute("student", student);
        model.addAttribute("studentList", da.getStudentList());
        return "index";
    }

    }





