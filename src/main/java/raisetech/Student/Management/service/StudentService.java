package raisetech.Student.Management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourses;
import raisetech.Student.Management.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

//サービスとして使える、認識される。
//サービスは実際の処理はサービスの中ででやる。
//例）2つのデータを結びつけたり、もらったデータを加工することもある。
@Service
public class StudentService {

    //コンスラクタで呼びですのがルール
    private StudentRepository repository;

    //フィールドインジェクションの問題点
    @Autowired
    public StudentService(StudentRepository repository){
        this.repository = repository;
    }


    public List<StudentsCourses> searchstudentsCoureses(){
        //絞り込み検索でjavaコースの情報のみを抽出する
        //抽出したリストをコントローラーに返す。
        //コントローラー側触らない


        return repository.searchBystudentCourese().stream()
                .filter(studentcoure -> studentcoure.getCourseName().equals("Java"))
                .collect(Collectors.toList());

    }

    //検索処理


    public List<Student> searchStudentList(){
        repository.searchBystudent();
        //絞り込みをする。年代が30代のひとのみを抽出する
        //抽出したリストをコントローラーに返す
        //コントローラー側触らない
        return repository.searchBystudent().stream()
                .filter(student -> student.getAge() >= 30 && student.getAge() < 40)
                .collect(Collectors.toList());

    }


}
