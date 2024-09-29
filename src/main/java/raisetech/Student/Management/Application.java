package raisetech.Student.Management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


	@GetMapping("/studentlist")
	public List<Student>  getStudentList(){
		return repository.searchBystudent();

	}
	@GetMapping("/studentcourseslist")
	public List<StudentsCourses>  getstudentcourseslist(){
		return repository.searchBystudentCourese();

	}


}
