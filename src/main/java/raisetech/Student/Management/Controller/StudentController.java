package raisetech.Student.Management.Controller;


import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import raisetech.Student.Management.ExceptionHandler.TestException;
import raisetech.Student.Management.domain.StudentDetail;
import raisetech.Student.Management.service.StudentService;

/**
 * 受講生の検索や登録、更新などをおこうなうREST　APIとして実行されるControllerクラス
 */
@Validated
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
    @GetMapping("/errtest")
    public void errTest() throws TestException{
        throw new TestException("現在このAPIは利用できません、");
    }

    /**
     * 受講生詳細一覧検索
     * 全件検索を行うので、条件指定は行わないものになります。
     *
     * @return　受講生一覧（全件）
     */

    @GetMapping("/studentlist")
    public List<StudentDetail> getStudentList() {
        return service.searchStudentList();
    }



    /**
     * 受講生詳細の登録を行います
     *
     *
     * @param studentDetail　受講生詳細
     * @return　実行結果
     */

    @PostMapping("/registerStudent")
    //StudentDetailオブジェクトにも＠Validを適用させる必要がある
    public ResponseEntity<StudentDetail> registerStudent(@RequestBody @Valid StudentDetail studentDetail){
         	return ResponseEntity.ok(service.insert(studentDetail));
    }



    @GetMapping("/student/{id}")
    //@PathVariableで{id}"を引数のString idに入れてくれる
    //1~3桁までしかいれれない
    public StudentDetail getStudent(@PathVariable @Size(min=1, max = 3) String id){
        return  service.searchStudentbyId(Integer.parseInt(id));

    }

    /**
     * 受講生詳細の更新を行います。キャンセルフラグの更新もここで行います。
     *
     * @param studentDetail　受講生小アイ
     * @return　実行結果
     */
    @PutMapping("/updateStudent")
    //@ModelAttribute→@RequestBodyでRestか　 BindingResult resultは画面でのやつやから、消す
    public ResponseEntity<StudentDetail> updateStudent(@RequestBody @Valid StudentDetail studentDetail){
        if(studentDetail.getStudent().getDeleteFlag() ==null){
            studentDetail.getStudent().setDeleteFlag(0);
        }
        for(int i = studentDetail.getStudentsCourseList().size()-1; i >=0; i--) {
            String courseName = studentDetail.getStudentsCourseList().get(i).getCourseName();
        	if(courseName == null || courseName.isBlank()) {
        		studentDetail.getStudentsCourseList().remove(i);
        	}
        }

        service.update(studentDetail);
        return ResponseEntity.ok(studentDetail);
    }
    
    



}
