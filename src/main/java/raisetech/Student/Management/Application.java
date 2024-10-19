package raisetech.Student.Management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class Application {

	//メインメソッドはあまり使わない
	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}


}
