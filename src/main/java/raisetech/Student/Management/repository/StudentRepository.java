package raisetech.Student.Management.repository;

import org.apache.ibatis.annotations.*;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourses;
import raisetech.Student.Management.domain.StudentDetail;

import java.util.List;


/**
 * 受講生情報を扱うリポジトリ
 *
 * 全件、条件検索、コース検索が行えるクラス
 */
//擬似的なデータベースみたいなもの
//@MapperがつくとMyBatisが管理するということを自動的に認識してくれる
@Mapper
public interface StudentRepository {
    /**
     * 全件検索をします
     */
    @Select("SELECT * FROM students")
    List<Student> searchBystudent();
    @Select("SELECT * FROM students_courses")
    List<StudentsCourses> searchBystudentCourese();
    @Insert("INSERT INTO students(ID ,name,name_ruby,nickname,email_address,area,age,gender,remark,delete_flag) " +
            "VALUES (#{student.id},#{student.name},#{student.nameRuby},#{student.nickname},#{student.emailAddress},#{student.area}, #{student.age}, #{student.remark},#{student.gender},1)")
    void insertByStudent(StudentDetail studentDetail);
    @Insert("INSERT INTO students_courses(ID ,student_ID,course_name,start_date,end_expected_date) " +
            "VALUES (#{studentsCourses[0].id},#{student.id},#{studentsCourses[0].courseName},#{studentsCourses[0].startDate},#{studentsCourses[0].endExpectedDate})")
    void insertByStudentCourse(StudentDetail studentDetail);


}
