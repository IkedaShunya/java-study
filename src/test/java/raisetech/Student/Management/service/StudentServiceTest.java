package raisetech.Student.Management.service;

import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.Student.Management.Controller.converter.StudentConverter;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourse;
import raisetech.Student.Management.domain.StudentDetail;
import raisetech.Student.Management.repository.StudentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

//サービスの振る舞いをテスト
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {


    //モック・スタブ化(Mockitoを使う)　インスタンスを作ってからのやつを作ってくれる
    //repositoryの中は中身のない実装
    //もしモック化しなかったらインターフェイスの実装をしないといけなくなる
    @Mock
    private StudentRepository repository;

    @Mock
    private StudentConverter converter;

    private StudentService sut;


    //@BeforeAllはこのクラス全体で一回だけ
    //@BeforeEachはテストメソッドの都度動く（セットアップ）
    @BeforeEach
    void before(){
        //repositoryとconverterはモック化されたやつ使いますよ StudentService sut =new StudentService(repository,converter);がいらなくなる
        sut = new StudentService(repository, converter);
    }

    @Test
    //受講生検索と受講生コース検索とコンバーターの呼び出しができること
    void 受講生詳細の一覧検索＿リポジトリとコンバーター処理が適切に呼び出せていること(){
        //＜事前準備＞
        //DIコンテナ勝手に上がるわけではないしspringbootをタチがあげるわけではないからnewしてインスタンス化する
        //テスト対象はsut(SystemunderTest)が多い
        //StudentService sut =new StudentService(repository,converter);
        //こなっているいるであろう値を渡して
        // List<StudentDetail> expected = new ArrayList<>();　　

        List<Student> studentList = new ArrayList<>();
        List<StudentsCourse> studentsCourseList = new ArrayList<>();

        //repositoryのsearchBystudentを呼び出した時にはにstudentList(空)を返しますよ
        Mockito.when(repository.searchBystudent()).thenReturn(studentList);
        when(repository.searchBystudentCourese()).thenReturn(studentsCourseList);



        //＜実行＞
        //テスト対象のメソッドを呼び出す
        //sut.searchStudentList();
        //→returnでList<StudentDetail> searchStudentListを返しているので
        sut.searchStudentList();



        //＜検証＞
        //→ テストの検証の対象　List<StudentDetail> actual = sut.searchStudentList();
        //こなっているいるであろう値（expected）と実際の値（actual）を比較する
        //Assertions.assertEquals(expected, actual);

        //serviceはほとんどverify
        //verify(xx,times(num)).yy xxインスタンスのyyメソッドをnum回呼び出したか検証　ダメだったらエラー分がでる、expectedいらなくなる

        //↓はsearchBystudent();とsearchBystudentCourese();で実行した値を入れなければならない
        verify(converter,times(1)).convertstudentDetails(studentList, studentsCourseList);

        verify(repository,times(1)).searchBystudent();
        verify(repository,times(1)).searchBystudentCourese();
        //＜後処理＞

    }


    @ParameterizedTest
    @ValueSource(ints = {4})
    //受講生IDを引数として、受講生検索ができること
    void 受講生検索_IDを引数としてリポジトリを呼び出しがきるていること(Integer id){
        // モックの設定
        Student student = new Student();
        student.setId(id);
        List<StudentsCourse> studentsCourseList =List.of(new StudentsCourse(), new StudentsCourse());

        // リポジトリのモックが返すデータを定義
        //repositoryのsearchBystudentを呼び出した時にはにstudentList(空)を返しますよ
        //=sut.searchStudentbyId(id);用
        when(repository.searchIdBystudent(id)).thenReturn(student);
        when(repository.searchcouresbystudentid(id)).thenReturn(studentsCourseList);

        StudentDetail expect = new StudentDetail(student , studentsCourseList);

        StudentDetail actual =sut.searchStudentbyId(id);


        assertNotNull(actual);
        assertEquals(expect.getStudent().getId(), actual.getStudent().getId());
        assertEquals(2, actual.getStudentsCourseList().size());

        verify(repository,times(1)).searchIdBystudent(id);
        verify(repository,times(1)).searchcouresbystudentid(id);
    }

    @Test
        //受講生検索と受講生コース検索とコンバーターの呼び出しができること
    void 受講生詳細の登録＿リポジトリとコンバーター処理が適切に呼び出せていること(){
        Student student = new Student();
 //       student.setId(1);
        StudentsCourse studentsCourse1 = new StudentsCourse();
        studentsCourse1.setStartDate(LocalDate.now());
        List<StudentsCourse> studentsCourseList = new ArrayList<>();
        studentsCourseList.add(studentsCourse1);
        StudentDetail studentDetail = new StudentDetail(student, studentsCourseList);

        //repositoryのsearchBystudentを呼び出した時にはにstudentList(空)を返しますよ
        doNothing().when(repository).insertByStudent(student);
        doNothing().when(repository).insertByStudentCourse(any(StudentsCourse.class));

        StudentDetail actual = sut.insert(studentDetail);



        verify(repository,times(1)).insertByStudent(student);

        verify(repository,times(1)).insertByStudentCourse(any(StudentsCourse.class));

        assertEquals(studentDetail, actual);

        //＜後処理＞


    }

    @Test
    void 受講生詳細の更新_受講生とコースが正しく更新されること() {
        // 事前準備
        StudentDetail studentDetail = new StudentDetail();
        Student student = new Student();
        studentDetail.setStudent(student);
        StudentsCourse studentsCourse1 = new StudentsCourse();
        studentsCourse1.setStartDate(LocalDate.now());
        StudentsCourse studentsCourse2 = new StudentsCourse();
        studentsCourse2.setStartDate(LocalDate.now());

        List<StudentsCourse> studentCourses = List.of(studentsCourse1,studentsCourse2);
        studentDetail.setStudentsCourseList(studentCourses);


        doNothing().when(repository).updateByStudent(student);

        doNothing().when(repository).insertByStudentCourse(any(StudentsCourse.class));

        // 実行
        sut.update(studentDetail);

        // 検証
        verify(repository, times(1)).updateByStudent(student);
        verify(repository, times(2)).insertByStudentCourse(any(StudentsCourse.class)); // 2つのコースが更新されることを検証
    }

    @Test
    void 受講生詳細の登録_初期化処理が行われること(){
        Integer id = 999;
        Student student = new Student();
        student.setId(id);
        StudentsCourse studentsCourse = new StudentsCourse();
        studentsCourse.setStartDate(LocalDate.now());

        sut.initStudentsCourse(studentsCourse, student);


        Assertions.assertEquals(id,studentsCourse.getStudentid());
        Assertions.assertEquals(LocalDate.now().plusYears(1), studentsCourse.getEndExpectedDate());






    }




}