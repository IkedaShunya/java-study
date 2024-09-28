package raisetech.Student.Management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class Application {

	//@Autowired でspringが管理しているインスタンスを自動的にrepositoryをあてはめてくれる
	//new しなくてもよい（インターフェイスはnewできない）
	// StudentRepositoryのインスタンス生成をしてくれる
	@Autowired
	private StudentRepository repository;


	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	//引数（名前）を指定してStudentRepositoryのselect文を実行
	@GetMapping("/student")
	//@RequestParamは（）内の変数のパラメータを指定できる　例）?name=ikedashunya
	public String getStudentInfo(@RequestParam("name")String name){
		Student student =repository.searchByName(name);
		return student.getName() + student.getAge() + "歳";

	}

	//引数（名前と年齢）を指定してStudentRepositoryのINSERT文を実行
	@PostMapping("/student")
	public void registerStudent(String name, int age){
		repository.registerStudent(name, age);
	}

	@PatchMapping("/student")
	public void updateStudentAge(String name, int age){
		repository.updateStudent(name,age);
	}

	@DeleteMapping("/student")
	public void deleteStudent(String name){
		repository.deleteStudent(name);
	}





}
