package raisetech.Student.Management.data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    private String emailAddress;
    private String area;
    private int age;
    private String gender;
    private String remark;
    private Integer deleteFlag;

}
