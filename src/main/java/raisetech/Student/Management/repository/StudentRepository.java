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

    /**
     * ID検索
     */
    @Select("SELECT * FROM students WHERE id IN (#{id}) ")
    Student searchIdBystudent(int id);

    @Select("SELECT * FROM students_courses WHERE student_ID IN (#{studentID}) ")
    List<StudentsCourses>  searchCouresbystudentID(int id);

    @Insert("INSERT INTO students(name,name_ruby,nickname,email_address,area,age,gender,remark,delete_flag) " +
            "VALUES (#{name}, #{nameRuby}, #{nickname}, #{emailAddress}, #{area}, #{age}, #{gender} ,#{remark},0)")
    // @Options(useGeneratedKeys = true, keyProperty = "id" ) idが自動採番と伝える
    @Options(useGeneratedKeys = true, keyProperty = "id" )
    void insertByStudent(Student student);
    @Insert("INSERT INTO students_courses(student_ID,course_name,start_date,end_expected_date) " +
            "VALUES (#{studentID}, #{courseName}, #{startDate}, #{endExpectedDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id" )
    void insertByStudentCourse(StudentsCourses studentsCourses);


}
