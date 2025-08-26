package com.elon.jdbc.day03.pstmt.member.view;

import java.util.List;
import java.util.Scanner;

import com.elon.jdbc.day03.pstmt.member.controller.MemberController;
import com.elon.jdbc.day03.pstmt.member.model.vo.Member;



public class MemberView {
	private MemberController mController;
	
	public MemberView(){
		mController = new MemberController();
	}
	
	// run에서 시작할 메서드
	public void startProgram() {
		Member member;
		String memberId;
		int result = 0;
		finish:
		while(true) {
			int choice = this.printMenu();
			switch(choice){
			case 1: 
				member = addMember();
				result = mController.registerMember(member);
				if(result > 0) {
					printMessage("회원 정보 저장완료");
				} else {
					printMessage("회원 정보 저장실패");
				}
				break;
			case 2: 
				List<Member> mList = mController.showMemberList();
				printAllMember(mList);
				break;
			case 3: 
				// 아이디 입력받기
				memberId = inputMemberID(); 
				// DB에서 회원정보 가져오기
				member = mController.findOneById(memberId);
				//출력하기
				printOne(member);
				break;
			case 4: 
				// 아이디 받기
				memberId = inputMemberID();
				member = mController.findOneById(memberId);
				if(member != null) {	
					// 수정할 정보 받기
					member = modifyMember(memberId);
					result = mController.updateMember(member);
					if(result > 0) {
						printMessage("회원 정보 수정완료");
					} else {
						printMessage("회원 정보 수정실패");
					}
				} else {
					printMessage("해당 회원이 존재하지 않습니다.");
				}
				break;
			case 5: 
				memberId = inputMemberID();
				member = mController.findOneById(memberId);
				if(member != null) {
					result = mController.deleteMember(memberId);
					// 입력 받은 아이디로 회원 정보가 존재하는지
					if (result > 0) {
						printMessage("회원 삭제완료");
					} else {
						printMessage("회원 삭제실패");
					}
				} else {
					printMessage("해당 회원이 존재하지 않습니다.");
				}
				break;
			case 0: 
				this.printMessage("프로그램을 종료합니다.");
				break finish;
			default:
				this.printMessage("1 ~ 5 사이의 숫만 입력해주세요.");
			}
 		}
	}
	
	// 아이디 검색시 아이디 입력
	private String inputMemberID() {
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 입력 : ");
		return sc.next();
	}
	
	// 학생 정보 추가시 학생정보 입력받기
	private Member addMember() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("아이디 : ");
		String memberId = sc.next();
		
		System.out.print("비밀번호 : ");
		String memberPwd = sc.next();
		
		System.out.print("이름 : ");
		String memberName = sc.next();
		
		System.out.print("성별 : ");
		char gender = sc.next().toUpperCase().charAt(0);
		
		System.out.print("나이 : ");
		int age = sc.nextInt();
		
		System.out.print("이메일 : ");
		String email = sc.next();
		
		System.out.print("전화번호 : ");
		String phone = sc.next();
		
		System.out.print("주소 : ");
		sc.nextLine(); // 주소입력시 스페이스바 꼅침 방지
		String address = sc.nextLine();
		
		System.out.print("취미 : ");
		String hobby = sc.next();
		
		Member member = new Member(memberId, memberPwd
				, memberName, gender, age
				, email, phone, address, hobby);
		
		return member;
	}
	
	// 학생 정보 수정시 수정정보 입력받기
	private Member modifyMember(String memberId) {
		Scanner sc = new Scanner(System.in);

		System.out.print("수정할 비밀번호 입력: ");
		String newPwd = sc.next();
		
		System.out.print("수정할 이메일 입력: ");
		String newEmail = sc.next();
		
		System.out.print("수정할 전화번호 입력: ");
		String newPhone = sc.next();
		
		System.out.print("수정할 주소 입력: ");
		sc.nextLine(); // 주소입력시 스페이스바 꼅침 방지
		String newAddress = sc.nextLine();
		
		System.out.print("수정할 취미 입력: ");
		String newHobby = sc.next();
		
		Member member = new Member(memberId, newPwd, newEmail, newPhone, newAddress, newHobby);
		return member;
	}
	
	// 학생 1개 정보 출력
	private void printOne(Member member) {
		System.out.println("==== 회원 정보 출력 ====");
		System.out.println("아이디\t\t이름\t\t이메일\t\t\t전화번호\t\t주소");
			System.out.println(member.getMemberId()+"\t"+member.getMemberName()
			+"\t\t"+member.getEmail()+"\t"+member.getPhone()+"\t\t"+member.getAddress());
	}
	
	// 학생 전체 정보 출력
	private void printAllMember(List<Member> mList) {
		System.out.println("==== 회원 전체 정보 ====");
		System.out.println("아이디\t\t이름\t\t이메일\t\t\t전화번호\t\t주소");
		for(Member member : mList) {
			System.out.println(member.getMemberId()+"\t"+member.getMemberName()
			+"\t\t"+member.getEmail()+"\t"+member.getPhone()+"\t\t"+member.getAddress());

		}
	}
	
	// 메시지 출력
	private void printMessage(String message) {
		System.out.println(message);
	}
	
	// 메뉴 출력
	private int printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 회원 관리 프로그램(day03, pstmt) ======");
		System.out.println("1. 회원 가입");
		System.out.println("2. 회원 정보 전체 조회");
		System.out.println("3. 회원 검색(아이디)");
		System.out.println("4. 회원 정보 수정");
		System.out.println("5. 회원 정보 삭제");
		System.out.println("0. 프로그램 종료");
		System.out.print("선택 >> ");
		return sc.nextInt();
		/*
		 * 1. 회원 가입
		 * 2. 회원 정보 전체 조회
		 * 3. 회원 검색(아이디)
		 * 4. 회원 정보 수정
		 * 5. 회원 정보 삭제
		 * 0. 프로그램 종료
		 */
	}
}
