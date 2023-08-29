package co.yedam.project;

import java.util.Scanner;

import co.yedam.project.common.SHA256;
import co.yedam.project.game.Game;
import co.yedam.project.users.menu.UsersMenu;
import co.yedam.project.users.service.UsersService;
import co.yedam.project.users.service.UsersVO;
import co.yedam.project.users.serviceImpl.UsersServiceImpl;

public class MainMenu {
	private Scanner sc = new Scanner(System.in);
	private UsersService dao = new UsersServiceImpl();
	private UsersMenu um = new UsersMenu();
	private Game gm = new Game();
	UsersVO vo = new UsersVO();

	public void run() {
		boolean b = false;
		if (usersLogin()) {
			do {
				title();
				int key = sc.nextInt();
				switch (key) {
				case 1:
					gm.run(vo.getUsersId());
					break;
				case 2:
					um.run(vo.getUsersId());
					break;
				case 3:
					b = true;
					sc.close();
					System.out.println("종료합니다.");
					break;
				}
			} while (!b);
		}
	}

	private void title() {
		System.out.println("==================");
		System.out.println("===숫자 맞추기 게임====");
		System.out.println("==================");
		System.out.println("=1. 게 임 시 작======");
		System.out.println("=2. 유 저 관 리======");
		System.out.println("=3. 종      료======");
		System.out.println("원하는 메뉴를 선택하세요");

	}

	private boolean usersLogin() {
		SHA256 sha256 = new SHA256();
		boolean b = false;
		System.out.println("==================");
		System.out.println("====1. 로그인=======");
		System.out.println("====2. 회원가입======");
		System.out.println("메뉴를 선택하세요");
		int key = sc.nextInt();
		sc.nextLine();
		if (key == 1) {
			System.out.println("회원 아이디를 입력하세요.");
			vo.setUsersId(sc.nextLine());
			System.out.println("회원 비밀번호를 입력하세요.");
			vo.setUsersPassword(sc.nextLine());
			vo.setUsersPassword(sha256.encrypt(vo.getUsersPassword()));
			vo = dao.usersSelect(vo);
			if (vo != null) {
				System.out.println(vo.getUsersName() + "님 환영합니다.");
				b = true;
			} else {
				System.out.println("아이디 및 비밀번호가 일치하지 않습니다.");
				System.out.println("시스템을 재시작하세요.");
			}
		} else if (key == 2) {
			System.out.println("아이디를 입력하세요.");
			vo.setUsersId(sc.nextLine());
			System.out.println("비밀번호를 입력하세요.");
			vo.setUsersPassword(sc.nextLine());
			vo.setUsersPassword(sha256.encrypt(vo.getUsersPassword()));
			System.out.println("이름을 입력하세요.");
			vo.setUsersName(sc.nextLine());
			try {
				if (dao.usersInsert(vo) != 0) {
					System.out.println("등록이 완료되었습니다.");
					b = true;
				}
			} catch (Exception e) {
				System.out.println("이미 존재하는 아이디 입니다.");
				System.out.println("시스템을 재시작하세요.");
				return b;
			}

		}
		return b;
	}
}
