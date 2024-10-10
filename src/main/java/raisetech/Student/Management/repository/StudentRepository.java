package raisetech.Student.Management.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourses;


/**
 * 受講生情報と受講生コース情報を扱うリポジトリ
 * 全件、条件検索、コース検索が行えるクラス
 */

@Mapper
public interface StudentRepository {
    /**
     * 全件検索をします
     *
     * @return 受講生一覧（全件）
     */
    @Select("SELECT * FROM students WHERE delete_flag = 0 ORDER BY id")
    List<Student> searchBystudent();

    /**
     * 受講生のコース情報の全件検索を行います
     * @return　受講生コース情報（全件）
     */
    @Select("SELECT * FROM students_courses WHERE student_id IN (SELECT id FROM students WHERE delete_flag = 0) ORDER BY id")
    List<StudentsCourses> searchBystudentCourese();

    /**
     * IDに紐づく受講生の検索を行います
     * @param  id 受講生ID
     * @return　受講生
     */
    @Select("SELECT * FROM students WHERE id IN (#{id}) ORDER BY id ")
    Student searchIdBystudent(int id);

    /**
     * IDに紐づく受講生コース情報の検索を行います
     * @param  id 受講生ID
     * @return　受講生コース情報
     */
    @Select("SELECT * FROM students_courses WHERE student_id IN (#{studentid}) ORDER BY id")
    List<StudentsCourses>  searchCouresbystudentID(int id);


    @Insert("INSERT INTO students(name,name_ruby,nickname,email_address,area,age,gender,remark,delete_flag) " +
            "VALUES (#{name}, #{nameRuby}, #{nickname}, #{emailAddress}, #{area}, #{age}, #{gender} ,#{remark},0)")
    // @Options(useGeneratedKeys = true, keyProperty = "id" ) idが自動採番と伝える
    @Options(useGeneratedKeys = true, keyProperty = "id" )
    void insertByStudent(Student student);
    @Insert("INSERT INTO students_courses(student_id,course_name,start_date,end_expected_date) " +
            "VALUES (#{studentid}, #{courseName}, #{startDate}, #{endExpectedDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id" )
    void insertByStudentCourse(StudentsCourses studentsCourses);
    
    
    
    @Update("UPDATE students SET name=#{name}, name_ruby=#{nameRuby}, nickname=#{nickname},email_address=#{emailAddress},area=#{area},age=#{age},gender=#{gender},remark=#{remark}, delete_flag=#{deleteFlag} WHERE id=#{id}")
    void updateByStudent(Student student);
    @Update("UPDATE students_courses SET course_name=#{courseName},start_date=#{startDate}"
    		+ ",end_expected_date=#{endExpectedDate} WHERE id = #{id}")
    void updateByStudentCourse(StudentsCourses studentsCourses);


}
