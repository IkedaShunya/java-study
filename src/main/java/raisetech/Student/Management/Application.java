package raisetech.Student.Management;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@OpenAPIDefinition(info = @Info(title ="受講生管理システム"))
@SpringBootApplication
public class Application {

	//メインメソッドはあまり使わない
	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}


}
