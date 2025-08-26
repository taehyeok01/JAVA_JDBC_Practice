package com.elon.jdbc.day02.stmt.member.controller;

import java.util.List;

import com.elon.jdbc.day02.stmt.member.model.dao.MemberDAO;
import com.elon.jdbc.day02.stmt.member.model.vo.Member;

public class MemberController {
	private MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}
	public Member findOneById(String memeberId) {
		return mDao.selectOneById(memeberId);
	}
	
	public List<Member> showMemberList() {
		List<Member> mList = mDao.selecList();
		return mList;
	}
	
	public int registerMember(Member member) {
		int result = mDao.insertMember(member);
		return result;
	}
	
	public int updateMember(Member member) {
		int result = mDao.updateMember(member);
		return result;
	}
	
	public int deleteMember(String memberId) {
		int result = mDao.deleteMember(memberId);
		return result;
	}
}
