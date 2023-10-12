package ca.sherdanc.on.elzeind.lec51_H2andJDBCTemplate.controllers;

import ca.sherdanc.on.elzeind.lec51_H2andJDBCTemplate.beans.Student;
import ca.sherdanc.on.elzeind.lec51_H2andJDBCTemplate.databases.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class StudentController {
    Student student = new Student();
    @Autowired
    private DatabaseAccess da;
    List<Student> studentList = new CopyOnWriteArrayList<>();
    List<String> nameList = new ArrayList<>();
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
    @PostMapping("/insertStudent")
    public String insertStudent(Model model, @ModelAttribute Student student) {
        nameList.add(student.getName());
        List<Student> existingStudents = da.getStudentListById(student.getId());

        if (existingStudents.isEmpty()) {
            // If the student doesn't exist (based on ID), insert a new student
            da.insertStudent(student);
        } else {
            // If the student exists, update the existing student
            da.updateStudent(student);
        }

        model.addAttribute("studentList", da.getStudentList());
        model.addAttribute("student", new Student());
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
    public String deleteStudentById(Model model, @PathVariable Long id){
        da.deleteStudentById(id);
        model.addAttribute("student", new Student());
        model.addAttribute("studentList", da.getStudentList());
        return "index";
    }
    @GetMapping("/editStudentById/{id}")
    public String editStudentById(Model model, @PathVariable Long id) {
        Student student = da.getStudentListById(id).get(0);
        model.addAttribute("student", student);
        model.addAttribute("studentList", da.getStudentList());
        return "index";
    }

}



