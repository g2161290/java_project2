package co.yedam.project;

import java.util.Scanner;

import co.yedam.project.board.menu.BoardMenu;
import co.yedam.project.common.SHA256;
import co.yedam.project.game.BullsAndCows;
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
	private BullsAndCows bac = new BullsAndCows();
	private BoardMenu bm = new BoardMenu();
	UsersVO vo = new UsersVO();

	public void run() {
		boolean b = false;
		if (usersLogin()) {
			do {
				title();
				int key = sc.nextInt();
				switch (key) {
				case 1:
					// 숫자퀴즈
					gm.run(vo.getUsersId());
					break;
				case 2:
					// 숫자야구
					bac.run(vo.getUsersId());
					break;
				case 3:
					// 유저관리
					um.run(vo.getUsersId());
					break;
				case 4:
					// 유저관리
					bm.run(vo.getUsersId());
					break;
				case 5:
					// 종료
					b = true;
					System.out.println("\r\n"
							+ "        _______   __   __   _______ \r\n"
							+ "       |  _    | |  | |  | |       |\r\n"
							+ "       | |_|   | |  |_|  | |    ___|\r\n"
							+ "       |       | |       | |   |___ \r\n"
							+ "       |  _   |  |_     _| |    ___|\r\n"
							+ "       | |_|   |   |   |   |   |___ \r\n"
							+ "       |_______|   |___|   |_______|\r\n"
							+ "");
					sc.close();
					break;
				}
			} while (!b);
		}
		sc.close();
	}

	private void title() {
		System.out.println("     ○==============================○");
		System.out.println("     |          ★숫 자 게 임★          |");
		System.out.println("     ○==============================○");
		System.out.println("     |    1.숫자퀴즈   |    2.숫자야구    |");
		System.out.println("     ○------------------------------○");
		System.out.println("     |    3.유저관리   |    4.게시판     |");
		System.out.println("     ○------------------------------○");
		System.out.println("     |    5.종료                      |");
		System.out.println("     ○==============================○");
		System.out.print("     메뉴 선택 >>");

	}

	private boolean usersLogin() {
		SHA256 sha256 = new SHA256();
		boolean b = false;
		System.out.println("\r\n"
				+ " ___      _______  _______  ___   __    _ \r\n"
				+ "|   |    |       ||       ||   | |  |  | |\r\n"
				+ "|   |    |   _   ||    ___||   | |   |_| |\r\n"
				+ "|   |    |  | |  ||   | __ |   | |       |\r\n"
				+ "|   |___ |  |_|  ||   ||  ||   | |  _    |\r\n"
				+ "|       ||       ||   |_| ||   | | | |   |\r\n"
				+ "|_______||_______||_______||___| |_|  |__|\r\n"
				+ "");
			System.out.println("     ○==============================○");
			System.out.println("     |    1. 로그인   |    2.회원가입    |");
			System.out.println("     ○==============================○");
			System.out.print("     메뉴 선택 >>");
			int key = sc.nextInt();
			sc.nextLine();
			if (key == 1) {
				System.out.print("     회원 아이디 >>");
				vo.setUsersId(sc.nextLine());
				System.out.print("     회원 비밀번호 >>");
				vo.setUsersPassword(sc.nextLine());
				vo.setUsersPassword(sha256.encrypt(vo.getUsersPassword()));
				vo = dao.usersSelect(vo);
				if (vo != null) {
					System.out.println("     "+vo.getUsersName() + "님 환영합니다.");
					b= true;
				} else {
					System.out.println("     아이디 및 비밀번호가 일치하지 않습니다.");
					System.out.println("     시스템을 재시작해 주세요");
				}
			} else if (key == 2) {
				System.out.print("     아이디 >>");
				vo.setUsersId(sc.nextLine());
				System.out.print("     비밀번호 >>");
				vo.setUsersPassword(sc.nextLine());
				vo.setUsersPassword(sha256.encrypt(vo.getUsersPassword()));
				System.out.print("     이름 >>");
				vo.setUsersName(sc.nextLine());
				try {
					if (dao.usersInsert(vo) != 0) {
						System.out.println("     등록이 완료되었습니다.");
						b = true;
					}
				} catch (Exception e) {
					System.out.println("     이미 존재하는 아이디 입니다.");
					System.out.println("     시스템을 재시작해 주세요");
				}
			}
		return b;
	}
}
