package raisetech.Student.Management.Controller.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourse;
import raisetech.Student.Management.domain.StudentDetail;

/**
 * 受講生と受講生コース情報を受講生詳細に変換するもしくはその逆を行うコンバーター
 *
 */

@Component
public class StudentConverter {
    /**
     * 受講生に紐づく受講生コース情報をマッピングする
     * 受講生コース情報を受講生に対して複数存在するのでループ回して受講生詳細をくみたためる
     *
     *
     * @param studentList　受講生一覧
     * @param studentCourseList　受講生コース情報のリスト
     * @return　受講生詳細リスト
     */


    public List<StudentDetail>  convertstudentDetails(List<Student> studentList, List<StudentsCourse> studentCourseList) {
        List<StudentDetail> studentDetails = new ArrayList<>();
        for (Student student : studentList) {
            StudentDetail studentDetail = new StudentDetail();
            studentDetail.setStudent(student);


            // equalsを使って比較
            List<StudentsCourse> convertStudnetCourseList =new ArrayList<>();
            for (StudentsCourse studentsCourse : studentCourseList) {
                if (student.getId().equals(studentsCourse.getStudentid())) {
                    convertStudnetCourseList.add(studentsCourse);
                }
            }


            //山田のstudent.getId　＝全件studentsCourse.getStudentID())　がリスとにはいる
            studentDetail.setStudentsCourseList(convertStudnetCourseList);
            studentDetails.add(studentDetail);
        }
        return studentDetails;
    }

    //  nList<StudentDetail> studetDetails = getStudentDetails(students, studentsCourses);
//        //検索してきた受講生の数だけループする 5人います
//        for(Student student : students){
//            //インスタンス作成　山田のStudent情報がはいる（全員はいる）
//            StudentDetail studentDetail = new StudentDetail();
//            studentDetail.setStudent(student);
//
//            //↑の全件を絞り込むためのリストが必要
//            //StudentsCourses情報全部ループする
//            List<StudentsCourses> convertStudnetCourses = new ArrayList<>();
//            for(StudentsCourses studentsCourse : studentsCourses){
//                //山田のstudent.getId　＝全件studentsCourse.getStudentID())　がリスとにはいる
//                if(student.getId().equals(studentsCourse.getStudentID())){
//                    convertStudnetCourses.add(studentsCourse);
//                }
//            }
//
//            //山田にひもづくStudent情報とStudentsCourses情報がはいる
//            studentDetail.setStudentsCourses(convertStudnetCourses);
//            studentDetails.add(studentDetail);
//        }
}
