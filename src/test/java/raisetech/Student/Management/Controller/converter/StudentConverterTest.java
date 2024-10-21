package raisetech.Student.Management.Controller.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import raisetech.Student.Management.Controller.StudentController;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourse;
import raisetech.Student.Management.domain.StudentDetail;
import raisetech.Student.Management.service.StudentService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



class StudentConverterTest {

    @BeforeEach
    void setUp() {

    }


    @Test
    void 受講生と受講生コース情報を受講生詳細に変換する＿正常系(){
        // テストデータ作成
        List<Student> studentList  = new ArrayList<>();
        Student student1 = new Student();
        student1.setId(1);
        student1.setName("Alice");
        studentList.add(student1);
        Student student2 = new Student();
        student2.setId(2);
        student2.setName("Jhon");
        studentList.add(student2);
        Student student3 = new Student();
        student3.setId(3);
        student3.setName("test");
        studentList.add(student3);
        Student student4 = new Student();
        student4.setId(4);
        student4.setName("test2");
        studentList.add(student4);

        List<StudentsCourse>  studentCourseList =new ArrayList<>();
        StudentsCourse course1 = new StudentsCourse();
        course1.setStudentid(1);
        course1.setCourseName("Math");
        studentCourseList.add(course1);
        StudentsCourse course2 = new StudentsCourse();
        course2.setStudentid(1);
        course2.setCourseName("Science");
        studentCourseList.add(course2);
        StudentsCourse course3 = new StudentsCourse();
        course3.setStudentid(1);
        course3.setCourseName("History");
        studentCourseList.add(course3);

        StudentConverter sut = new StudentConverter();

        // 実行
        List<StudentDetail> actual = sut.convertstudentDetails(studentList, studentCourseList);

        assertEquals(4, actual.size());
        assertEquals(1, actual.get(0).getStudent().getId());
        assertEquals("History",actual.get(0).getStudentsCourseList().get(2).getCourseName());

        assertEquals(4, actual.get(3).getStudent().getId());
        assertEquals(0, actual.get(3).getStudentsCourseList().size());

    }



}