package raisetech.Student.Management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import raisetech.Student.Management.Controller.converter.StudentConverter;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourses;
import raisetech.Student.Management.domain.StudentDetail;
import raisetech.Student.Management.repository.StudentRepository;

/**
 *受講生情報を扱うサービスです、
 *受講生の検索登録更新処理を行います
 *
 */
@Service
public class StudentService {

	//コンスラクタで呼びですのがルール
	private StudentRepository repository;
	private StudentConverter converter;

	//フィールドインジェクションの問題点
	@Autowired
	public StudentService(StudentRepository repository,StudentConverter converter){
		this.repository = repository;
		this.converter = converter;
	}

	public List<StudentDetail> searchStudentList(){
		List<Student> students = repository.searchBystudent();
		List<StudentsCourses> studentsCourses = repository.searchBystudentCourese();
		return converter.convertstudentDetails(students, studentsCourses);
	}




	/**
	 * 受講生検索です、
	 * IDに紐づく受講生情報を取得した後、その受講生に紐づく受講生コース情報を取得して設定します
	 *
	 * @return
	 */

	//IDで受講者情報の検索
	public StudentDetail searchStudentbyId(Integer id){
		Student student = repository.searchIdBystudent(id);
		List<StudentsCourses> studentCourses = repository.searchCouresbystudentID(id);


		return new StudentDetail(student,studentCourses);

	}

	//IDで受講者コース情報の検索
	public List<StudentsCourses> searchStudentCouresbyId(StudentsCourses studentCourse){
		List<StudentsCourses> studentCourses = repository.searchCouresbystudentID(studentCourse.getStudentid());
		return studentCourses;
	}



	//@Transactional　トランザクション処理をする。　serviceに入れる必要がある。
	@Transactional
	public StudentDetail insert(StudentDetail studentDetail){

		repository.insertByStudent(studentDetail.getStudent());
		//↑の処理が終わったらStudentオブジェクトの中にIdは入っている
		//そのため、studentDetail.getStudent().getId() は自動採番したやつが入っている
		//TODO：コース情報管理を行う
		for(StudentsCourses studentsCourse : studentDetail.getStudentsCourses()){
			studentsCourse.setStudentid(studentDetail.getStudent().getId());
			//始める日から1年後が終了予定日です
			studentsCourse.setEndExpectedDate(studentsCourse.getStartDate().plusYears(1));
			repository.insertByStudentCourse(studentsCourse);
		}
		return studentDetail;
	}


	@Transactional
	public void update(StudentDetail studentDetail){

		repository.updateByStudent(studentDetail.getStudent());
		for(StudentsCourses studentsCourse : studentDetail.getStudentsCourses()){
			
			studentsCourse.setEndExpectedDate(studentsCourse.getStartDate().plusYears(1));
			if(studentsCourse.getStudentid() == null) {
				//StudentID()がない場合はinsert(コース名と開始日を与えて)する
				studentsCourse.setStudentid(studentDetail.getStudent().getId());
				repository.insertByStudentCourse(studentsCourse);
			}else {
				repository.updateByStudentCourse(studentsCourse);
			}
			
			
		}
	}


}
