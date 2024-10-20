package raisetech.Student.Management.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
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
    
    @Operation(summary = "テスト用例外処理",description = "テスト用に例外処理をスローします")
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

    @Operation(summary = "受講生詳細一覧検索",description = "受講生詳細の一覧を検索します")
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
    	//1.クライアントから送信されたリクエストボディ (例えば、JSON形式の学生情報) が StudentDetail オブジェクトに変換されます
        //2.@Valid によって、StudentDetail オブジェクトがバリデーションされます。バリデーションに失敗した場合、400 Bad Requestのエラーが返されます。
        //3.バリデーションが成功した場合、service.insert(studentDetail) によって学生情報が登録 (または保存) されます。
        //4.保存された学生情報を含むレスポンスが 200 OK ステータスとともに返されます。

    @Operation(summary = "受講生登録",description = "受講生を登録します")
    @PostMapping("/registerStudent")
    //StudentDetailオブジェクトにも＠Validを適用させる必要がある
    //
    public ResponseEntity<StudentDetail> registerStudent(@RequestBody @Valid StudentDetail studentDetail){
         	return ResponseEntity.ok(service.insert(studentDetail));
    }


    @Operation(summary = "受講生単一検索",description = "受講生IDから受講生の情報を検索します")
    @GetMapping("/student/{id}")
    //@PathVariableで{id}"を引数のString idに入れてくれる
    //1~3桁までしかいれれない
    public StudentDetail getStudent(@PathVariable  @Valid @Size(min=1, max = 3) String id){
        return  service.searchStudentbyId(Integer.parseInt(id));

    }

    /**
     * 受講生詳細の更新を行います。キャンセルフラグの更新もここで行います。
     *
     * @param studentDetail　受講生小アイ
     * @return　実行結果
     */
    @Operation(summary = "受講生情報更新",description = "受講生の詳細情報の検索を行います")
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
