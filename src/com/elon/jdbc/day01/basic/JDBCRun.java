package com.elon.jdbc.day01.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRun {
	private final static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final static String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private final static String USER = "KH";
	private final static String PASSWORD = "KH";
	/*
	 * 1. 드라이버 등록
	 * 2. DBMS 연결
	 * 3. Statement 연결
	 * 4. SQL 전송
	 * 5. 결과받기 - 후처리
	 * 6. 자원해제
	 */
	public static void main(String[] args) {
		updateRun();
	}
	
	public static void updateRun() {
		String query = "UPDATE EMPLOYEE SET SALARY = 2000000 WHERE EMP_ID = '200'";
		Connection conn = null;
		Statement stmt = null;
		// ResultSet은 insert, delete는 필요없고, select는 필요함
		int result = -1;
		
		try {
			Class.forName(DRIVER_NAME); // static에서 사용할꺼라 DRIVER_NAME에 static써야함
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			if(result > 0) {
				System.out.println("데이터 수정 완료");
			} else {
				System.out.println("데이터 수정 실패");
			}
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
	}
	
	public static void insertRun() {
		String query = "INSERT INTO EMPLOYEE VALUES('201','송종기','631126-1548654','song_jk@kh.or.kr','01045686656','D9','J2','S1',6000000, null,'200',to_date('01/09/01','YYYY/MM/DD'),null,'N')";
		Connection conn = null;
		Statement stmt = null;
		// ResultSet은 insert, delete는 필요없고, select는 필요함
		int result = -1;
		
		try {
			Class.forName(DRIVER_NAME); // static에서 사용할꺼라 DRIVER_NAME에 static써야함
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			if(result > 0) {
				System.out.println("데이터 추가 완료");
			} else {
				System.out.println("데이터 추가 실패");
			}
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
	}
	
	public static void deleteRun() {
		String query = "DELETE FROM EMPLOYEE WHERE EMP_ID = 202";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		int result = -1;
		
		try {
			// 1. 드라이버 등록
			Class.forName(DRIVER_NAME);
			// 2. DBMS 연결
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			// 3. Statement 생성
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			conn.setAutoCommit(false); // 자동 커밋
			if(result > 0) { // 0보다 크면 성공
				conn.commit(); // 성공했으면 커밋
				System.out.println("데이터 삭제 완료");
			} else { // 아니면 실패
				conn.rollback(); // 실패했으면 롤백
				System.out.println("데이터 삭제 실패");
			}
			
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
	}
	
	public static void selectRun() {
		// 깔끔하게 가져올 데이터를 변수화 한다.
//		String driver ="oracle.jdbc.driver.OracleDriver";
//		String url = "jdbc:oracle:thin:@localhost:1521:XE";
//		String user = "KH";
//		String password = "KH";
		// 쿼리문도 변수화
		String query = "SELECT * FROM EMPLOYEE";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			// 1. 드라이버 등록
			Class.forName(DRIVER_NAME);
			// 2. DBMS 연결,
//			Connection conn = DriverManager.getConnection(url, user, password); // 접속하기
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			// new Statement 안됨 Interface라 안됨
//			Statement stmt = conn.createStatement(); // 워크시트 열기
			stmt = conn.createStatement();
//			ResultSet rset = stmt.executeQuery(query); // 컨트롤 + 엔터
			rset = stmt.executeQuery(query);
			// 후처리
			while(rset.next()) {
				System.out.println("사번 : " + rset.getString("EMP_ID") + "\t이름 : " + rset.getString("EMP_NAME"));
			}
			// 중간에 exception 발생시 실행안될수도있음
//			rset.close();
//			stmt.close();
//			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}




