package raisetech.Student.Management;

import org.apache.ibatis.annotations.*;

//擬似的なデータベースみたいなもの
//@MapperがつくとMyBatisが管理するということを自動的に認識してくれる
@Mapper
public interface StudentRepository {

    //#{name} はjava(アプリ)のほうのname WHERE nameはSQL文のname
    @Select("SELECT * FROM student WHERE name = #{name}")
    Student searchByName(String name);

    @Insert("INSERT student values(#{name}, #{age})")
    void  registerStudent(String name, int age);

    @Update("UPDATE student SET age = #{age} WHERE name = #{name}")
    void updateStudent(String name, int age);

    @Delete("DELETE FROM student WHERE name = #{name}")
    void deleteStudent(String name);

}
