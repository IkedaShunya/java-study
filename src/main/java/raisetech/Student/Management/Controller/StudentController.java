package raisetech.Student.Management.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourses;
import raisetech.Student.Management.service.StudentService;

import java.util.List;

@RestController
public class StudentController {

    private StudentService service;


    @Autowired
    public StudentController(StudentService service){
        this.service = service;
    }


    @GetMapping("/studentlist")
    //ユーザー側なのでget
    public List<Student> getStudentList(){
        //リクエストの加工、入力チェックとか
        return service.searchStudentList();

    }
    @GetMapping("/studentcourseslist")
    public List<StudentsCourses>  getstudentcourseslist(){
        return service.searchstudentsCoureses();

    }
}
