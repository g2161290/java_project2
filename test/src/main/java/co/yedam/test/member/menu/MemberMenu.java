package co.yedam.test.member.menu;

import java.util.List;
import java.util.Scanner;

import co.yedam.test.common.SHA256;
import co.yedam.test.member.service.MemberService;
import co.yedam.test.member.service.MemberVO;
import co.yedam.test.member.serviceImpl.MemberServiceImpl;

public class MemberMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberService dao = new MemberServiceImpl();
	private SHA256 sha256 = new SHA256();
	
	private void memberTitle() {
		System.out.println("====================");
		System.out.println("====멤 버 관 리====");
		System.out.println("=== 1. 멤버 전체 조회===");
		System.out.println("=== 2. 멤버 한건 조회===");
		System.out.println("=== 3. 멤버 등록===");
		System.out.println("=== 4. 멤버 수정===");
		System.out.println("=== 5. 멤버 삭제===");
		System.out.println("==== 6. 홈 가 기 ======");
		System.out.println("작업번호를 선택하세요?");
	}
	
	public void run() {
		boolean b = false;
		do {
			memberTitle();
			int key = sc.nextInt();
			sc.nextLine();
			switch(key) {
			case 1:
				memberSelectList();
				break;
			case 2:
				break;
			case 3:
				memberInsert();
				break;
			case 4:
				
				break;
			case 5:
				
				break;
			case 6:
				b=true;
				break;
			}
		}while(!b);
	}



	private void memberInsert() {
		MemberVO vo =new MemberVO();
		System.out.println("======회 원 등 록==========");
		System.out.println("회원 아이디(이메일)을 입력하세요.");
		vo.setMemberId(sc.nextLine());
		System.out.println("회원 패스워드를 입력하세요.");
		vo.setMemberPassword(sc.nextLine());
		System.out.println("회원 이름을 입력하세요.");
		vo.setMemberName(sc.nextLine());
		System.out.println("회원 전화번호를 입력하세요."); 
		vo.setMemberTel(sc.nextLine());
		vo.setMemberPassword(sha256.encrypt(vo.getMemberPassword()));
		
		int n=dao.memberInsert(vo);
		if(n!=0) {
			System.out.println("정상적으로 등록이 완료되었습니다.");
		} else {
			System.out.println("등록이 실패 하였습니다.");
		}
	}

	private void memberSelectList() {
		List<MemberVO> members = dao.memberSelectList();
		System.out.println("======멤 버 목 록==========");
		System.out.println("  아이디  : 이름 :  전화번호   :  가입일자 ");
		
		for(MemberVO m : members) {
			System.out.print(m.getMemberId() +" : ");
			System.out.print(m.getMemberName() +" : ");
			System.out.print(m.getMemberTel() +" : ");
			System.out.println(m.getMemberEnterDate());
		}
	}
}
