package raisetech.Student.Management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import raisetech.Student.Management.repository.StudentRepository;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourses;

import java.util.List;

@SpringBootApplication
@RestController
public class Application {

	//メインメソッドはあまり使わない
	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}


}
