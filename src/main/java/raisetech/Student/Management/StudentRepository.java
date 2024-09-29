package raisetech.Student.Management;

import org.apache.ibatis.annotations.*;

import java.util.List;

//擬似的なデータベースみたいなもの
//@MapperがつくとMyBatisが管理するということを自動的に認識してくれる
@Mapper
public interface StudentRepository {

    @Select("SELECT * FROM students")
    List<Student> searchBystudent();
    @Select("SELECT * FROM students_courses")
    List<StudentsCourses> searchBystudentCourese();


}
