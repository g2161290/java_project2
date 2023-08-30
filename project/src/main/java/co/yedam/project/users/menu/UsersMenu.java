package co.yedam.project.users.menu;

import java.util.List;
import java.util.Scanner;

import co.yedam.project.MainMenu;
import co.yedam.project.common.SHA256;
import co.yedam.project.users.service.UsersService;
import co.yedam.project.users.service.UsersVO;
import co.yedam.project.users.serviceImpl.UsersServiceImpl;

public class UsersMenu {
	private Scanner sc = new Scanner(System.in);
	private UsersService dao = new UsersServiceImpl();
	

	private void usersTitle() {
		System.out.println("     ○==============================○");
		System.out.println("     |           유 저 관 리           |");
		System.out.println("     ○==============================○");
		System.out.println("     |     1. 전체 등수 보기            |");
		System.out.println("     ○------------------------------○");
		System.out.println("     |     2. 유저 점수 보기            |");
		System.out.println("     ○------------------------------○");
		System.out.println("     |     3. 내 정보 수정하기           |");
		System.out.println("     ○------------------------------○");
		System.out.println("     |     4. 내 계정 삭제하기           |");
		System.out.println("     ○------------------------------○");
		System.out.println("     |     5. 홈으로 돌아가기            |");
		System.out.println("     ○==============================○");
		System.out.print("     메뉴 선택>>");
	}

	public void run(String id) {
		boolean b = false;
		do {
			usersTitle();
			int key = sc.nextInt();
			sc.nextLine();
			switch (key) {
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
				break;
			case 5:
				b = true;
				break;
			}
		} while (!b);
	}

	private void usersDelete(String id) {
		UsersVO vo = new UsersVO();
		System.out.println("     "+id + " 삭제하시겠습니까?(y/n 로그아웃됩니다.)");
		if (sc.nextLine().equals("y")) {
			vo.setUsersId(id);
			dao.usersDelete(vo);
			MainMenu mm = new MainMenu();
			mm.run();
		}
	}

	private void usersUpdate(String id) {
		UsersVO vo = new UsersVO();
		vo.setUsersId(id);
		System.out.println("     ○==============================○");
		System.out.println("     |   1. 비밀번호    |    2.이름     |");
		System.out.println("     ○------------------------------○");
		System.out.println("     |   3.취소                      |");
		System.out.println("     ○==============================○");
		System.out.print("     메뉴 선택 >>");
		
		int key = sc.nextInt();
		sc.nextLine();
		switch (key) {
		case 1:
			System.out.print("     비밀번호 >>");
			SHA256 sha256 = new SHA256();
			vo.setUsersPassword(sc.nextLine());
			vo.setUsersPassword(sha256.encrypt(vo.getUsersPassword()));
			break;
		case 2:
			System.out.print("     이름 >>");
			vo.setUsersName(sc.nextLine());
			break;
		case 3:
			break;
		}
		if (dao.usersUpdate(vo) != 0) {
			System.out.println("     수정이 완료되었습니다.");
		}
	}

	private void usersSelectList() {

		System.out.println("     ○==============================○");
		System.out.println("     |   1.숫자퀴즈    |   2.숫자야구    |");
		System.out.println("     ○==============================○");
		System.out.print("     게임 선택>>");
		int n = sc.nextInt();
		System.out.println("     ○============유 저 목 록==========○");
		System.out.println("     아이디 : 이름 : 최고기록");
		if (n == 1) {
			List<UsersVO> users = dao.usersNumberquizSelectList();
			for (UsersVO u : users) {
				System.out.print("     "+u.getUsersId() + " : ");
				System.out.print(u.getUsersName() + " : ");
				System.out.println(u.getUsersNumberquizScore());
			}
		} else if (n == 2) {
			List<UsersVO> users = dao.usersBullsandcowsSelectList();
			for (UsersVO u : users) {
				System.out.print("     "+u.getUsersId() + " : ");
				System.out.print(u.getUsersName() + " : ");
				System.out.println(u.getUsersBullsandcowsScore());
			}
		}
	}

	private void usersSelect() {
		UsersVO vo = new UsersVO();
		System.out.print("     유저 아이디>>");
		String id = sc.nextLine();
		vo.setUsersId(id);
		vo = dao.usersSelect(vo);
		if (vo != null) {
			System.out.println("     아이디 : 이름 : (숫자퀴즈)최고기록 : 날짜 (숫자야구)최고기록 : 날짜");
			System.out.print("     "+vo.getUsersId() + " : ");
			System.out.print(vo.getUsersName() + " : ");
			System.out.print(vo.getUsersNumberquizScore() + " : ");
			System.out.print(vo.getUsersNumberquizScoreDate() + " : ");
			System.out.print(vo.getUsersBullsandcowsScore() + " : ");
			System.out.println(vo.getUsersBullsandcowsScoreDate());
		} else {
			System.out.println("     존재하지 않는 아이디입니다.");
		}
	}
}
