package raisetech.Student.Management.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourses;

import java.util.List;

@Getter
@Setter
//引数を全く使わないコンストラクタ
@NoArgsConstructor
//全てを引数（Student student、List<StudentsCourses> studentsCourses）にしていコンストラクタ
@AllArgsConstructor
public class StudentDetail {
    private Student student;
    private List<StudentsCourses> studentsCourses;
}
