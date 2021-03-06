package com.kriscfoster.school.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping
    List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping("/{id}")
    Teacher getTeacher(@PathVariable Long id) {
        return teacherRepository.findById(id).get();
    }

    @PostMapping
    Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }
}
