package com.elon.jdbc.day03.pstmt.member.controller;

import java.util.List;

import com.elon.jdbc.day03.pstmt.member.model.dao.MemberDAO;
import com.elon.jdbc.day03.pstmt.member.model.service.MemberService;
import com.elon.jdbc.day03.pstmt.member.model.vo.Member;


public class MemberController {
	private MemberDAO mDao;
	private MemberService mService;
	
	public MemberController() {
		mDao = new MemberDAO();
		mService = new MemberService();
	}
	public Member findOneById(String memberId) {
		Member member = mService.selectOneById(memberId);
		return member;
	}
	
	public List<Member> showMemberList() {
		List<Member> mList = mService.selectList();
		return mList;
	}
	
	public int registerMember(Member member) {
		int result = mService.registerMember(member);
		return result;
	}
	
	public int updateMember(Member member) {
		int result = mService.updateMember(member);
//		int result = mDao.updateMember(member);
		return result;
	}
	
	public int deleteMember(String memberId) {
		int result = mService.deleteMember(memberId);
//		int result = mDao.deleteMember(memberId);
		return result;
	}
}
