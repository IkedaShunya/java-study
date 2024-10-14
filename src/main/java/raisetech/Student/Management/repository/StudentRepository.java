package raisetech.Student.Management.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourse;


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
    List<Student> searchBystudent();

    /**
     * 受講生のコース情報の全件検索を行います
     * @return　受講生コース情報（全件）
     */
    List<StudentsCourse> searchBystudentCourese();

    /**
     * IDに紐づく受講生の検索を行います
     * @param  id 受講生ID
     * @return　受講生
     */
    Student searchIdBystudent(int id);

    /**
     * IDに紐づく受講生コース情報の検索を行います
     * @param  id 受講生ID
     * @return　受講生コース情報
     */
    List<StudentsCourse> searchcouresbystudentid(int id);

    /**
     * 受講生を新規登録します
     * IDに関しては自動採番を行う
     *
     * @param student　受講生
     */
    void insertByStudent(Student student);


    /**
     * 受講生コース情報を新規登録します
     * IDに関しては自動採番を行う
     *
     * @param studentsCourses　受講生コース情報
     */
    void insertByStudentCourse(StudentsCourse studentsCourses);


    /**
     * 受講生を更新します
     * @param student
     */
    void updateByStudent(Student student);

    /**
     * 受講生コース情報のコース名を更新します
     *
     * @param studentsCourse
     */
    void updateByStudentCourse(StudentsCourse studentsCourse);


}
