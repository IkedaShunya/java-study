package raisetech.Student.Management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class Application {

	//日本語を使うとエラーが発生する
	private String name ="ikeda shunay";
	private String age = "26";
	//Map<String, Integer> student = new HashMap<String, Integer>();

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	//コミット用
	@GetMapping("/studentInfo")
	public String getStudentInfo(){
		return name + "　" + age +"歳";

	}



	//名前（URL）を分ける場合が多い
	@PostMapping("/studentInfo")
	public void setName(String name, String age){
		this.name =name;
		this.age = age;
	}
	@PostMapping("/studentName")
	public void updateStudentName(String name){
		this.name =name;
	}


}
