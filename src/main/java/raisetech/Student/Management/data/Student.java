package raisetech.Student.Management.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生情報")
@Getter
@Setter
//Mybatisが自動的にDBの情報をセットしてくれるいれもの
public class Student {
    //DBと同じ名前で紐づく
    @NotNull(message = "名前をいれてください")
    private String name;


    private Integer id;
    //キャメルケースのルールで大文字になる
    @Size(min=1, max = 3)
    private String nameRuby;
    private String nickname;
    @Email
    private String emailAddress;
    private String area;
    private int age;
    private String gender;
    private String remark;
    private Integer deleteFlag;

}
