package raisetech.Student.Management.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@RestControllerAdvice
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    // @ExceptionHandler例外発生時の処理はしていする
    //TestExceptionの例外をまずキャッチする

    @ExceptionHandler(TestException.class)
    public ResponseEntity<String> handleTestException(TestException ex){
        //400番の内容で("現在このAPIは利用できません、"の内容を含めることができる

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }




}
