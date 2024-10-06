package raisetech.Student.Management.Controller.converter;

import org.springframework.stereotype.Component;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourses;
import raisetech.Student.Management.domain.StudentDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentConverter {
    public List<StudentDetail>  convertstudentDetails(List<Student> students, List<StudentsCourses> studentsCourses) {
        List<StudentDetail> studentDetails = new ArrayList<>();
        students.forEach(student -> {
            StudentDetail studentDetail = new StudentDetail();
            studentDetail.setStudent(student);


            List<StudentsCourses> convertStudnetCourses =
                    studentsCourses.stream().filter(studentsCourse -> student.getId()
                                    == (studentsCourse.getStudentID()))
                            .collect(Collectors.toList());


            //山田のstudent.getId　＝全件studentsCourse.getStudentID())　がリスとにはいる
            studentDetail.setStudentsCourses(convertStudnetCourses);
            studentDetails.add(studentDetail);
        });
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
