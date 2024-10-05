package raisetech.Student.Management.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller
public class StudentController {

    private StudentService service;
    private StudentConverter converter;


    @Autowired
    public StudentController(StudentService service, StudentConverter converter){
        this.service = service;
        this.converter = converter;
    }

    //コントローラー側の処理（Java） @Controllerと記載する

    @GetMapping("/studentlist")
    //Stringを戻り値とする
    public String getStudentList(Model model){
        List<Student> students = service.searchStudentList();
        List<StudentsCourses> studentsCourses = service.searchstudentsCoureses();

        //モデルはhtmlファイルとコントローラーの間のいるやつ？
        //"studentlist"の中身はconverter.convertstudentDetails(students, studentsCourses)だよ
        model.addAttribute("students",converter.convertstudentDetails(students, studentsCourses));

        //テンプレートエンジンの名前を返す。
        // 上のやつと一致させなければならないわけではない
        return "studentlist";


    }


    //Stringを戻り値とする
    @GetMapping("/studentcourseslist")
    public String  getstudentcourseslist(Model model){
        model.addAttribute("studentsCourses",service.searchstudentsCoureses());
        return "studentcourslist";

    }
}
