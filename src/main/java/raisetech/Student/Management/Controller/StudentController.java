package raisetech.Student.Management.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import raisetech.Student.Management.Controller.converter.StudentConverter;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourses;
import raisetech.Student.Management.domain.StudentDetail;
import raisetech.Student.Management.service.StudentService;

/**
 * 受講生の検索や登録、更新などをおこうなうREST　APIとして実行されるControllerクラス
 */
@RestController
public class StudentController {


    private StudentService service;


    /**
     *コンストラクタ
     *
     * @param service　受講生サービス
     */

    @Autowired
    public StudentController(StudentService service){
        this.service = service;

    }

    /**
     * 受講生一覧検索
     * 全件検索を行うので、条件指定は行わないものになります。
     *
     * @return　受講生一覧（全件）
     */

    @GetMapping("/studentlist")
    public List<StudentDetail> getStudentList(){

        return service.searchStudentList();
    }




    @PostMapping("/registerStudent")
    //th:object="${student}の名前が引数になっている
    //@ModelAttribute はModel(StudentDetail student)に対してHTMLでPOSTの引数を入れますよ
    // BindingResult result は入力チェック（Studentクラスでバリデーションチェック(@つけるだけ)できる　文字の長さとか）
    //ここに入った時点でチェックしてくれる　
    public ResponseEntity<StudentDetail> registerStudent(@RequestBody StudentDetail studentDetail){
        //新規受講生情報を登録する処理を実装する
        //コース情報も一緒に登録できるように実装する。コースは単体でいい。


    	return ResponseEntity.ok(service.insert(studentDetail));


     
    }


    /**
     * 受講生検索です、
     * IDに紐づく任意の受講生の情報を取得します、
     *
     * @param id 受講生ID
     * @return
     */
    @GetMapping("/student-edit/{id}")
    //@PathVariableで{id}"を引数のString idに入れてくれる
    public StudentDetail getStudent(@PathVariable String id , Model model){


        return  service.searchStudentbyId(Integer.parseInt(id));

    }
    
    @PostMapping("/updateStudent")
    //@ModelAttribute→@RequestBodyでRestか　 BindingResult resultは画面でのやつやから、消す
    public ResponseEntity<StudentDetail> updateStudent(@RequestBody StudentDetail studentDetail){
        if(studentDetail.getStudent().getDeleteFlag() ==null){
            studentDetail.getStudent().setDeleteFlag(0);
        }
        //新規受講生情報を登録する処理を実装する//
        //コース情報も一緒に登録できるように実装する。コースは単体でいい。
        for(int i =studentDetail.getStudentsCourses().size()-1; i >=0; i--) {
            String courseName = studentDetail.getStudentsCourses().get(i).getCourseName();
        	if(courseName == null || courseName.isBlank()) {
        		studentDetail.getStudentsCourses().remove(i);
        	}
        }
        
        service.update(studentDetail);
        //OKだった時のBodyの中身（"OK`ですとかでもいい"）
        return ResponseEntity.ok(studentDetail);
    }
    
    



}
