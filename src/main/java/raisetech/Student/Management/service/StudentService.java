package raisetech.Student.Management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import raisetech.Student.Management.Controller.converter.StudentConverter;
import raisetech.Student.Management.data.Student;
import raisetech.Student.Management.data.StudentsCourse;
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

	/**
	 * 受講生詳細の一覧検索です。全件検索を行うので、条件指定は行いません。
	 *
	 * @return　受講生一覧（全件）
	 */
	public List<StudentDetail> searchStudentList(){
		List<Student> students = repository.searchBystudent();
		List<StudentsCourse> studentCourseList = repository.searchBystudentCourese();
		return converter.convertstudentDetails(students, studentCourseList);
	}




	/**
	 * 受講生検索です、
	 * IDに紐づく受講生詳細情報を取得した後、その受講生に紐づく受講生コース情報を取得して設定します
	 * @param id 受講生ID
	 * @return　受講生詳細
	 */

	//IDで受講者情報の検索
	public StudentDetail searchStudentbyId(Integer id){
		Student student = repository.searchIdBystudent(id);
		List<StudentsCourse> studentCourseList = repository.searchcouresbystudentid(id);


		return new StudentDetail(student,studentCourseList);

	}


	/**
	 * 受講生詳細の登録を行います。
	 * 受講生と受講生コース情報を個別に登録して、受講生コース情報には受講生情報を紐づける値や日付情報（受講生開始日など）
	 *
	 * @param studentDetail　受講生詳細
	 * @return　登録を付与した受講生詳細
	 */
	@Transactional
	public StudentDetail insert(StudentDetail studentDetail){
		//準備
		Student student = studentDetail.getStudent();

		//やりたいことやる
		repository.insertByStudent(student);
        for (StudentsCourse studentsCourse : studentDetail.getStudentsCourseList()) {
            initStudentsCourse(studentsCourse, student);
            repository.insertByStudentCourse(studentsCourse);
        }

        return studentDetail;
	}

	/**
	 * 受講生コース情報を登録する際の初期情報を設定する
	 *
	 *
	 * @param studentsCourse　受講生コース情報
	 * @param student　受講生
	 */
	void initStudentsCourse(StudentsCourse studentsCourse, Student student) {
		studentsCourse.setStudentid(student.getId());
		studentsCourse.setEndExpectedDate(studentsCourse.getStartDate().plusYears(1));
	}

	/**
	 *
	 * 受講生詳細の更新を行う。受講生と受講生コース情報をそれぞれ更新します。
	 *
	 * @param studentDetail　
	 */
	@Transactional
	public void update(StudentDetail studentDetail){

		repository.updateByStudent(studentDetail.getStudent());
		for(StudentsCourse studentsCourse : studentDetail.getStudentsCourseList()){

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
