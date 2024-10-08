package raisetech.Student.Management.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import raisetech.Student.Management.Controller.converter.StudentConverter;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourses;
import raisetech.Student.Management.domain.StudentDetail;
import raisetech.Student.Management.service.StudentService;

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


    @GetMapping("/studentcourseslist")
    public String  getstudentcourseslist(Model model){
        model.addAttribute("studentsCourses",service.searchstudentsCoureses());
        return "studentcourslist";

    }

    @GetMapping("/newStudent")
    public String newStudent(Model model){
        //th:object="${studentDetail}はGetしたときも使われるが
        //表示するときに{studentDetail}がなければエラーが発生してします
        // そのためからのstudentDetailをセットする
        model.addAttribute("studentDetail", new StudentDetail());
        return  "registerStudent";
    }


    //registerStudent.htmlファイルの「<form th:action="@{/registerStudent}」
    //から飛んできている
    @PostMapping("/registerStudent")
    //th:object="${student}の名前が引数になっている
    //@ModelAttribute はModel(StudentDetail student)に対してHTMLでPOSTの引数を入れますよ
    // BindingResult result は入力チェック（Studentクラスでバリデーションチェック(@つけるだけ)できる　文字の長さとか）
    //ここに入った時点でチェックしてくれる　
    public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result){
        if(result.hasErrors()){
            return "registerStudent";
        }
        //新規受講生情報を登録する処理を実装する
        //コース情報も一緒に登録できるように実装する。コースは単体でいい。
        service.insert(studentDetail);


        //studentlistのページに飛ばす
        return "redirect:/studentlist";
    }
    

    //編集処理
    //画面から受け取ったIDをサービスに流す
    @GetMapping("/student-edit/{id}")
    //@PathVariableで{id}"を引数のString idに入れてくれる
    public String getStudent(@PathVariable String id ,Model model){
        Student student = new Student();
        student.setId(Integer.parseInt(id));
        student = service.searchStudentbyId(student.getId());

//        List<StudentsCourses> studentsCourses = new ArrayList<StudentsCourses>();
        StudentsCourses studentCourse = new StudentsCourses();
//        StudentsCourses studentCourse = new StudentsCourses();
        studentCourse.setStudentid(Integer.parseInt(id));
//        studentCourse.setStudentID(Integer.parseInt(id));

//        studentsCourses.add(studentCourse);
//        studentsCourses = service.searchStudentCouresbyId(studentsCourses);
        List<StudentsCourses> studentsCourses = service.searchStudentCouresbyId(studentCourse);



        StudentDetail studentDetail = new StudentDetail();
        studentDetail.setStudent(student);
        studentDetail.setStudentsCourses(studentsCourses);
        model.addAttribute("studentDetail", studentDetail);

        return  "studentedit";

    }
    
    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result){
        if(result.hasErrors()){
        	for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getObjectName() + " - " + error.getDefaultMessage());
            }
            return "registerStudent";
        }
        //新規受講生情報を登録する処理を実装する
        //コース情報も一緒に登録できるように実装する。コースは単体でいい。
        for(int i =studentDetail.getStudentsCourses().size()-1; i >=0; i--) {
            String courseName = studentDetail.getStudentsCourses().get(i).getCourseName();
        	if(courseName == null || courseName.isBlank()) {
        		studentDetail.getStudentsCourses().remove(i);
        	}
        }
        
        service.update(studentDetail);
        


        //studentlistのページに飛ばす
        return "redirect:/studentlist";
    }
    
    



}
