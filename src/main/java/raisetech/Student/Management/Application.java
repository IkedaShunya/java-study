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
	//private String name ="ikeda shunay";
	//private String age = "26";

	//Map.of メソッドはJava 9以降で導入され、不変（immutable）のマップを作成します。
	// この不変マップに対して put メソッドを呼び出すと、
	// UnsupportedOperationException がスローされます。
	private Map<String, Integer> student = new HashMap<>(Map.of(
			"ikeda",25,
			"enami",39));

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	//コミット用
	@GetMapping("/studentInfo")
	public Map<String,Integer> getStudentInfo(){
		return student;

	}

	//名前（URL）を分ける場合が多い
	@PostMapping("/studentInfo")
	public void setName(String name, int age){
		student.put(name, age) ;
	}


//	@PostMapping("/studentName")
//	public void updateStudentName(String name){
//		this.name =name;
//	}


}
