package raisetech.Student.Management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourses;
import raisetech.Student.Management.domain.StudentDetail;
import raisetech.Student.Management.repository.StudentRepository;

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


		return repository.searchBystudentCourese();
		/*.stream()
                .filter(studentcoure -> studentcoure.getCourseName().equals("Java"))
                .collect(Collectors.toList());*/

	}

	//検索処理


	public List<Student> searchStudentList(){
		repository.searchBystudent();
		//絞り込みをする。年代が30代のひとのみを抽出する
		//抽出したリストをコントローラーに返す
		//コントローラー側触らない
		return repository.searchBystudent();
		/*.stream()
                .filter(student -> student.getAge() >= 30 && student.getAge() < 40)
                .collect(Collectors.toList());*/

	}
	//@Transactional　トランザクション処理をする。　serviceに入れる必要がある。
	@Transactional
	public void insert(StudentDetail studentDetail){

		repository.insertByStudent(studentDetail.getStudent());
		//↑の処理が終わったらStudentオブジェクトの中にIdは入っている
		//そのため、studentDetail.getStudent().getId() は自動採番したやつが入っている
		//TODO：コース情報管理を行う
		for(StudentsCourses studentsCourse : studentDetail.getStudentsCourses()){
			studentsCourse.setStudentID(studentDetail.getStudent().getId());
			//始める日から1年後が終了予定日です
			studentsCourse.setEndExpectedDate(studentsCourse.getStartDate().plusYears(1));
			repository.insertByStudentCourse(studentsCourse);
		}
	}

	//IDで受講者情報の検索
	public Student searchStudentbyId(Integer id){
		return repository.searchIdBystudent(id);

	}

	//IDで受講者コース情報の検索
	public List<StudentsCourses> searchStudentCouresbyId(StudentsCourses studentCourse){
		//        for(StudentsCourses studentsCourse : studentsCourses) {
		//            studentsCourses.add(repository.searchCouresbystudentID(studentsCourse.getStudentID()));
		//        }
		List<StudentsCourses> studentCourses = repository.searchCouresbystudentID(studentCourse.getStudentID());
		return studentCourses;
	}

	@Transactional
	public void update(StudentDetail studentDetail){

		repository.updateByStudent(studentDetail.getStudent());
		for(StudentsCourses studentsCourse : studentDetail.getStudentsCourses()){
			
			studentsCourse.setEndExpectedDate(studentsCourse.getStartDate().plusYears(1));
			if(studentsCourse.getStudentID() == null) {
				//StudentID()がない場合はinsert(コース名と開始日を与えて)する
				studentsCourse.setStudentID(studentDetail.getStudent().getId());
				repository.insertByStudentCourse(studentsCourse);
			}else {
				repository.updateByStudentCourse(studentsCourse);
			}
			
			
		}
	}


}
