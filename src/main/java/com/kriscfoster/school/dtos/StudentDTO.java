package com.kriscfoster.school.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kriscfoster.school.student.Student;
import com.kriscfoster.school.subject.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter @Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

    String name;
    List<SubjectDTO> courses;// = new ArrayList<>();
    String[] courseTitles = null;

    public StudentDTO(Student student) {
       this.name = student.getName();
       courses = student.getSubjects().stream().map(subject -> new SubjectDTO(subject.getName())).collect(Collectors.toList());
    }
    public StudentDTO(String name,String[] courses) {
       this.name = name;
       courseTitles = courses;
    }

    public static List<StudentDTO> getListFromStudentList(Iterable<Student> students){
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for(Student s: students){
            studentDTOS.add(new StudentDTO((s)));
        }
        return studentDTOS;
        //Tomas will introduce Lambdas in Java. With lambdas you can do what we did above like this
        //return StreamSupport.stream(students.spliterator(),false).map(student -> new StudentDTO(student)).collect(Collectors.toList());
        // Or even simpler
        //return StreamSupport.stream(students.spliterator(),false).map(StudentDTO::new).collect(Collectors.toList());
    }

    public static List<StudentDTO> getListFromStudentListWithCourseTitles(Iterable<Student> students){
        List<StudentDTO> dtos = new ArrayList<>();

        for(Student student : students){
            String[] courses = student.getSubjects().stream().map(subject -> subject.getName()).collect(Collectors.toList()).toArray(new String[0]);
            dtos.add(new StudentDTO(student.getName(),courses));
        }
        return dtos;
    }
}
