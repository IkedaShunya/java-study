package raisetech.Student.Management.repository;

import org.apache.ibatis.annotations.*;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourses;

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
     *
     * 全件検索をします
     */
    @Select("SELECT * FROM students")
    List<Student> searchBystudent();
    @Select("SELECT * FROM students_courses")
    List<StudentsCourses> searchBystudentCourese();


}
