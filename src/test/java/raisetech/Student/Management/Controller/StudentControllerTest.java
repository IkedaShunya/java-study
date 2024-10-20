package raisetech.Student.Management.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourse;
import raisetech.Student.Management.domain.StudentDetail;
import raisetech.Student.Management.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService service;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void エラー処理が流れられること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/errtest"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void 受講生詳細の一覧検索が実行できて空のリストが返ってくること() throws Exception{
        when(service.searchStudentList()).thenReturn(List.of(new StudentDetail()));

        //Getメソッドの検証
        mockMvc.perform(MockMvcRequestBuilders.get("/studentlist"))
                //検証したい内容は何ですか？
                .andExpect(status().isOk())
                //どんなJsonの中身を期待していますか
                .andExpect(content().json("[{\"student\":null,\"studentsCourseList\":null}]"));

        verify(service, times(1)).searchStudentList();

    }
    @Test
    void 受講生情報の登録処理ができること() throws Exception{
        String id = "1";
        Student student = new Student();
        student.setName("Test Name");
        student.setId(Integer.parseInt(id));
        List<StudentsCourse> studentsCourseList = new ArrayList<>();
        StudentDetail studentDetail = new StudentDetail(student, studentsCourseList);

        when(service.insert(studentDetail)).thenReturn(studentDetail);

        // StudentDetail オブジェクトを JSON にシリアライズするために ObjectMapper を使用
        ObjectMapper objectMapper = new ObjectMapper();
        String studentDetailJson = objectMapper.writeValueAsString(studentDetail);


        mockMvc.perform(MockMvcRequestBuilders.post("/registerStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentDetailJson)) // ここでリクエストボディを指定
                .andExpect(status().isOk()) ;// 正常なレスポンスを期


    }


    @Test
    void 受講生の単一検索が実行できて空のリストが返ってくること() throws Exception{
        String id = "1";
        Student student = new Student();
        student.setId(Integer.parseInt(id));
        List<StudentsCourse> studentsCourseList = new ArrayList<>();
        StudentDetail studentDetail = new StudentDetail(student, studentsCourseList);

        when(service.searchStudentbyId(Integer.parseInt(id))).thenReturn(studentDetail);

        //Getメソッドの検証
        mockMvc.perform(MockMvcRequestBuilders.get("/student/1"))
                //検証したい内容は何ですか？
                .andExpect(status().isOk())
                //どんなJsonの中身を期待していますか
                .andExpect(content().json("{\"student\":{\"id\":1},\"studentsCourseList\":[]}"));

        verify(service, times(1)).searchStudentbyId(Integer.parseInt(id));

    }



    @Test
    void 受講生詳細の受講生IDに入力チェックに以上が発生しないこと(){
        Student student = new Student();
        student.setId(10);
        student.setName("aaa");
        student.setNameRuby("aaa");
        student.setEmailAddress("a@teste.c");


        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        //エラー内容の確認
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Student> violation : violations) {
                System.out.println("エラーが発生したフィールド: " + violation.getPropertyPath());
                System.out.println("エラーメッセージ: " + violation.getMessage());
                System.out.println("無効な値: " + violation.getInvalidValue());
            }
        } else {
            System.out.println("バリデーションは成功しました。");
        }

        //エラーの数の検証
        //Assertions.assertEquals(0, violations.size());
        assertThat(violations.size()).isEqualTo(0);


    }


    @Test
    void 受講生詳細の受講生IDに数字以外を用いた時に入力チェックにかかること(){
        Student student = new Student();
        student.setId(10);
        student.setName("aaa");
        student.setNameRuby("aaaa");
        student.setEmailAddress("a");


        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        //エラー内容の確認
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Student> violation : violations) {
                System.out.println("エラーが発生したフィールド: " + violation.getPropertyPath());
                System.out.println("エラーメッセージ: " + violation.getMessage());
                System.out.println("無効な値: " + violation.getInvalidValue());
            }
        } else {
            System.out.println("バリデーションは成功しました。");
        }

        //エラーの数の検証
        //Assertions.assertEquals(2, violations.size());
        assertThat(violations.size()).isEqualTo(2);
        assertThat(violations).extracting("message").containsOnly( "1 から 3 の間のサイズにしてください","電子メールアドレスとして正しい形式にしてください");

    }
}