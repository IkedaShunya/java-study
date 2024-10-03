package raisetech.Student.Management.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//Mybatisが自動的にDBの情報をセットしてくれるいれもの
public class Student {
    //DBと同じ名前で紐づく
    private String name;
    private String id;
    //キャメルケースのルールで大文字になる
    private String nameRuby;
    private String nickname;
    private String emailAddress;
    private String area;
    private int age;
    private String gender;
    private String remark;
    private int deleteFlag;

}
