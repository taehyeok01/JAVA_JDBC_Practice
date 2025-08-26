package com.elon.jdbc.day03.pstmt.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.elon.jdbc.day03.pstmt.member.common.JDBCTemplate;
import com.elon.jdbc.day03.pstmt.member.model.dao.MemberDAO;
import com.elon.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberService {
	
	private JDBCTemplate jdbctemplate;
	private MemberDAO mDao;
	
	public MemberService() {
		// JDBCTemplate 싱글톤으로 객체 생성
		jdbctemplate = JDBCTemplate.getInstance();
		mDao = new MemberDAO();
	}
	
	public List<Member> selectList() {
		// 서비스에서 연결 생성
		List<Member> mList = null;
		try {
			Connection conn = jdbctemplate.getConnection();
			mList = mDao.selectList(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 생성된 연결을 DAO로 전달
		return mList;
	}
	
	public Member selectOneById(String memberId) {
		Member member = null;
		try {
			Connection conn = jdbctemplate.getConnection();
			member = mDao.selectOneById(memberId, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	public int registerMember(Member member) {
		int result = 0;
		try {
			Connection conn = jdbctemplate.getConnection();
			result = mDao.insertMember(member, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateMember(Member member) {
		int result = 0;
		try {
			Connection conn = jdbctemplate.getConnection();
			result = mDao.updateMember(member, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteMember(String memberId) {
		int result = 0;
		try {
			Connection conn = jdbctemplate.getConnection();
			result = mDao.deleteMember(memberId, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
