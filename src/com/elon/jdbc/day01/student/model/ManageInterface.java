package com.elon.jdbc.day01.student.model;
import java.util.List;
public interface ManageInterface {
	Student searchOneByName(String name);
	/**
	 * 이름으로 Student 객체 찾기
	 * @param name 검색할 이름 
	 * @return 검색한 인덱스
	 */
	int searchIndexByName(String name);
	/**
	 * 리스트 모든 정보 리턴
	 * @return 학생 정보가 저장된
	 */
	List<Student> getAllStudent();
	/**
	 * 
	 * @return
	 */
	int addStudent(Student student);
	/**
	 * 리스트에 정보를 수정하는 메소드
	 * @param index 수정할 객체의 인덱스
	 * @param student 수정할 Student 객체
	 */
//	void setStudent(int index, Student student); 
	/**
	 *  리스트에 정보를 삭제하는 메소드
	 * @param index 삭제할 객체의 인덱스
	 */
//	void removeStudent(int index);
	
	int removeStudent(String name);
	
	int setName(Student student);
	
	void searchName(String name);
}
