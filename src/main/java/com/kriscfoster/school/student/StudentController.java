package com.kriscfoster.school.student;

import com.kriscfoster.school.dtos.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    Student getStudent(@PathVariable Long id) {

        return studentRepository.findById(id).get();
    }
    //@GetMapping("/v2")
    List<StudentDTO> getStudentsDTO() {
        return StudentDTO.getListFromStudentList(studentRepository.findAll());
    }

    //@GetMapping("/simple")
    List<StudentDTO> getStudentsV2() {
        return StudentDTO.getListFromStudentListWithCourseTitles(studentRepository.findAll());
    }

    @PostMapping
    Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable Long id) {

    }
}