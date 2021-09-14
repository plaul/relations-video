package com.kriscfoster.school.configuration;

import com.kriscfoster.school.student.Student;
import com.kriscfoster.school.student.StudentRepository;
import com.kriscfoster.school.subject.Subject;
import com.kriscfoster.school.subject.SubjectRepository;
import com.kriscfoster.school.teacher.Teacher;
import com.kriscfoster.school.teacher.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class DataSetup implements CommandLineRunner {

    StudentRepository studentRepository;
    TeacherRepository teacherRepository;
    SubjectRepository subjectRepository;

    public DataSetup(StudentRepository studentRepository, TeacherRepository teacherRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        long student1 = studentRepository.save(new Student("Student-1")).getId();
        long student2 = studentRepository.save(new Student("Student-2")).getId();
        long student3 = studentRepository.save(new Student("Student-3")).getId();
        long student4 = studentRepository.save(new Student("Student-4")).getId();

        long idProg = subjectRepository.save(new Subject("Programming")).getId();
        long idTec = subjectRepository.save(new Subject("Technique")).getId();
        long idSys = subjectRepository.save(new Subject("SYS")).getId();

        long idLars = teacherRepository.save(new Teacher("Lars")).getId();
        long idJon = teacherRepository.save(new Teacher("Jon")).getId();
        long idOscar = teacherRepository.save(new Teacher("Oscar")).getId();

        Subject prog = subjectRepository.findById(idProg).get();
        Teacher lars = teacherRepository.findById(idLars).get();


        lars.addSubject(prog);  //DON't look, but what must be taken care of in this method
        subjectRepository.save(prog);

        // //DON't look, but what must be taken care of in this method
        prog.addStudent(studentRepository.findById(student1).get());
        prog.addStudent(studentRepository.findById(student2).get());
        prog.addStudent(studentRepository.findById(student3).get());
        prog.addStudent(studentRepository.findById(student4).get());

        subjectRepository.save(prog);

        System.out.println("Courses : ->" + lars.getSubjects().size());


    }
}
