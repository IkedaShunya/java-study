package raisetech.Student.Management.data;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentsCourse {
    private Integer id;
    private Integer studentid;
    private String courseName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    private LocalDate endExpectedDate;



}
