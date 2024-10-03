package raisetech.Student.Management.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.Student.Management.Controller.converter.StudentConverter;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourses;
import raisetech.Student.Management.domain.StudentDetail;
import raisetech.Student.Management.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentController {

    private StudentService service;
    private StudentConverter converter;


    @Autowired
    public StudentController(StudentService service, StudentConverter converter){
        this.service = service;
        this.converter = converter;
    }


    @GetMapping("/studentlist")
    public List<StudentDetail> getStudentList(){
        List<Student> students = service.searchStudentList();
        List<StudentsCourses> studentsCourses = service.searchstudentsCoureses();

        return converter.convertstudentDetails(students, studentsCourses);


    }



    @GetMapping("/studentcourseslist")
    public List<StudentsCourses>  getstudentcourseslist(){
        return service.searchstudentsCoureses();

    }
}
