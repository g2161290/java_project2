package co.yedam.project.users.menu;

import java.util.List;
import java.util.Scanner;

import co.yedam.project.MainMenu;
import co.yedam.project.common.SHA256;
import co.yedam.project.users.service.UsersService;
import co.yedam.project.users.service.UsersVO;
import co.yedam.project.users.serviceImpl.UsersServiceImpl;

public class UsersMenu {
	private Scanner sc= new Scanner(System.in);
	private UsersService dao = new UsersServiceImpl();
	UsersVO vo = new UsersVO();
	
	private void usersTitle() {
		System.out.println("====================");
		System.out.println("===== 등 수 보 기 =====");
		System.out.println("=== 1. 전체 유저 보기===");
		System.out.println("=== 2. 유저 점수 보기===");
		System.out.println("=== 3. 정보 수정 하기===");
		System.out.println("=== 4. 정보 삭제 하기===");
		System.out.println("=== 5. 홈으로 돌아가기===");
		System.out.println("====================");
		System.out.println("메뉴를 선택하세요.");
	}
	public void run(String id) {
		boolean b = false;
		do {
			usersTitle();
			int key = sc.nextInt();
			sc.nextLine();
			switch(key) {
			case 1:
				usersSelectList();
				break;
			case 2:
				usersSelect();
				break;
			case 3:
				usersUpdate(id);
				break;
			case 4:
				usersDelete(id);
				b=true;
				break;
			case 5:
				b=true;
				break;
			}
		} while(!b);
	}
	private void usersDelete(String id) {
		System.out.println(id+" 삭제하시겠습니까?(y/n 로그아웃됩니다.)");
		if(sc.nextLine().equals("y")) {
			vo.setUsersId(id);
			dao.usersDelete(vo);
			MainMenu mm = new MainMenu();
			mm.run();
		} 
	}
	private void usersUpdate(String id) {
		vo.setUsersId(id);
		System.out.println("====================");
		System.out.println("= 수정할 항목을 선택하세요=");
		System.out.println("====================");
		System.out.println("=== 1. 비밀번호 ======");
		System.out.println("=== 2. 이름 =========");
		System.out.println("=== 3. 취소 =========");
		int key=sc.nextInt();
		sc.nextLine();
		switch(key) {
		case 1:
			System.out.println("= 비밀번호를 입력하세요 ==");
			SHA256 sha256 = new SHA256();
			vo.setUsersPassword(sc.nextLine());
			vo.setUsersPassword(sha256.encrypt(vo.getUsersPassword()));
			break;
		case 2:
			System.out.println("= 이름을 입력하세요 =====");
			vo.setUsersName(sc.nextLine());
			break;
		case 3:
			break;
		}
		if(dao.usersUpdate(vo)!=0) {
			System.out.println("수정이 완료되었습니다.");
		}
	}
	private void usersSelectList() {
		List<UsersVO> users = dao.usersSelectList();
		System.out.println("===== 유 저 목 록 =====");
		System.out.println(" 아이디 : 이름 : 최고기록");
		for(UsersVO u : users) {
			System.out.print(u.getUsersId()+" : ");
			System.out.print(u.getUsersName()+" : ");
			System.out.println(u.getUsersScore());
		}
	}
	private void usersSelect() {
		System.out.println("유저 아이디를 입력하세요");
		String id= sc.nextLine();
		vo.setUsersId(id);
		vo=dao.usersSelect(vo);
		if(vo!=null) {
			System.out.println(" 아이디 : 이름 : 최고기록 : 날짜");
			System.out.print(vo.getUsersId()+" : ");
			System.out.print(vo.getUsersName()+" : ");
			System.out.print(vo.getUsersScore()+" : ");
			System.out.println(vo.getUsersScoreDate());
		} else {
			System.out.println("존재하지 않는 아이디입니다.");
		}
	}
}
