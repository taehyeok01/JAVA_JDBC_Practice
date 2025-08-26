package com.elon.jdbc.day01.student.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManageStudent implements ManageInterface{
	private final static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final static String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private final static String USER = "ELONJDBC";
	private final static String PASSWORD = "ELONJDBC";
	private List<Student> sList;
	
	public ManageStudent() {
		// sList = new ArrayList<Student>();
	}
	

	@Override
	public Student searchOneByName(String name) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM STUDENT_TBL WHERE STUDENT_NAME = '" + name +"'";
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			// 후처리
			if (rset.next()) {
				String studentName = rset.getString("STUDENT_NAME");
				int firstScore = rset.getInt("FIRST_SCORE");
				int secondScore = rset.getInt("SECOND_SCORE");
				// rset에서 가져온 필드를 여러개를 리턴하지 못하므로
				// 객체 여러개를 담아서 리턴해야함
				Student student = new Student(studentName, firstScore, secondScore);
				return student;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
//		sList.contains(name); 불린형이라 있는지 없는지만 가능
//		for(int i = 0; i < sList.size(); i++) {
//			if(sList.get(i).getName().equals(name)){
//				return sList.get(i);
//			}
//		}
		return null;
	}
	
	@Override
	public int searchIndexByName(String name) {
		// TODO Auto-generated method stub
		for(int i = 0; i < sList.size(); i++) {
			if(sList.get(i).getName().equals(name)) {
				return i; // 원하는 인덱스 값 리턴
			}
		}
		return -1; // index가 0이면 첫번째 값이기 때문에 -1로 리턴 (인덱스는 0부터 시작)
	}


	@Override
	public int addStudent(Student student) {
		// TODO 정보가 입력된 student객체를 sList에 저장
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		String query = "INSERT INTO STUDENT_TBL VALUES('"+student.getName()+"',"
				+student.getFirstScore()+","+student.getSecondScore()+")";
//		sList.add(student);
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
//			if(result > 0) {
//				System.out.println("데이터 삽입 완료");
//			} else {
//				System.out.println("데이터 삽입 실패");
//			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

//	@Override
//	public void setStudent(int index, Student student) {
//		// TODO Auto-generated method stub
//		sList.set(index, student);
//		
//	}
	
	@Override
	public List<Student> getAllStudent() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM STUDENT_TBL";
		try {
			Class.forName(DRIVER_NAME); // 드라이버 등록
			conn = DriverManager.getConnection(URL, USER, PASSWORD); // 연결 생성
			stmt = conn.createStatement(); // 워크시트 열기
			rset = stmt.executeQuery(query); // 쿼리문 실행 후 결과 받기
			/* 
			 * 후처리
			 * rset에 있는 필드의 값을 Student의 필드에 하나씩 넣어줌
			 * Student 객체는 List에 저장해서 보내줌
			 */
			// 초기화
			sList = new ArrayList<Student>();
			while(rset.next()) {
				// 출력담당인 ViewStudent로 해줘야함
				// rset.getString("STUDENT_NAME");
				// rset.getString("FIRST_SCORE");
				// rset.getString("SECOND_SCORE");
				// 를 해줘야 출력이 되는데 ViewStudent에는 없음
				// 근데 sList를 매개변수로 받고 있어 sList에 넣어서 보내면됨.
				Student student = new Student();
				// setName이 문자열이면 문자열로 가져오고
				// setFirstScore이 숫자면 int형으로 가져오고
				// 자료형에 맞게 가져와야한다.
				// getter/setter를 보고 이름이면 이름, 점수면 점수에 맞게 써야됨.
				student.setName(rset.getString("STUDENT_NAME"));
				student.setFirstScore(rset.getInt("FIRST_SCORE"));
				student.setSecondScore(rset.getInt("SECOND_SCORE"));
				// sList가 누적이 되는중 
				// 초기화를 한번만해서 그럼
				sList.add(student);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				rset.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sList;
	}

//	@Override
//	public void removeStudent(int index) {
//		sList.remove(index);
//	}


	@Override
	public int removeStudent(String name) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
	    String query = "DELETE FROM STUDENT_TBL WHERE STUDENT_NAME = '" + name + "'";
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}


	@Override
	public int setName(Student student) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		String query = "UPDATE STUDENT_TBL SET FIRST_SCORE = " 
		+ student.getFirstScore() 
		+", SECOND_SCORE = " 
		+ student.getSecondScore()+ 
		" WHERE STUDENT_NAME LIKE '" +student.getName()+ "'";
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			
//			if(result > 0) {
//				System.out.println("데이터 수정 완료");
//			} else {
//				System.out.println("데이터 수정 오류");
//			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


	@Override
	public void searchName(String name) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet reset = null;
		String query = "SELECT * FROM STUDENT_TBL WHERE STUDENT_NAME LIKE '" + name + "'";

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			reset = stmt.executeQuery(query);
			
			boolean found = false;
			while(reset.next()) {
				found = true;
				String studentName = reset.getString("STUDENT_NAME");
	            int firstScore = reset.getInt("FIRST_SCORE");
	            int secondScore = reset.getInt("SECOND_SCORE");
	            System.out.println(studentName + " 학생의 1차 점수 : " + firstScore + "점, 2차 점수 : " + secondScore + "점 입니다.");
			}
			 if (!found) {
		            System.out.println(name + " 학생의 정보가 존재하지 않습니다.");
		        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				reset.close();
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
