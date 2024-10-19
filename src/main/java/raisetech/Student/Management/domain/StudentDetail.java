package raisetech.Student.Management.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourse;

import java.util.List;

@Schema(description = "受講生詳細")
@Getter
@Setter
//引数を全く使わないコンストラクタ
@NoArgsConstructor
//全てを引数（Student student、List<StudentsCourses> studentsCourses）にしていコンストラクタ
@AllArgsConstructor
public class StudentDetail {
    //
    @Valid
    private Student student;
    private List<StudentsCourse> studentsCourseList;
}
