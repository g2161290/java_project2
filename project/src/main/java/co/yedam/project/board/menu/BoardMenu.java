package co.yedam.project.board.menu;

import java.util.List;
import java.util.Scanner;

import co.yedam.project.board.service.BoardService;
import co.yedam.project.board.service.BoardVO;
import co.yedam.project.board.serviceImpl.BoardServiceImpl;
import co.yedam.project.users.service.UsersService;
import co.yedam.project.users.service.UsersVO;
import co.yedam.project.users.serviceImpl.UsersServiceImpl;

public class BoardMenu {
	private Scanner sc = new Scanner(System.in);
	private BoardService dao = new BoardServiceImpl();
	private BoardVO vo = new BoardVO();

	private void boardTitle() {
		System.out.println("     ○==============================○");
		System.out.println("     |            게 시 판            |");
		System.out.println("     ○==============================○");
		System.out.println("     |     1. 전체 글 목록             |");
		System.out.println("     ○------------------------------○");
		System.out.println("     |     2. 글 자세히 보기            |");
		System.out.println("     ○------------------------------○");
		System.out.println("     |     3. 글 작성 하기             |");
		System.out.println("     ○------------------------------○");
		System.out.println("     |     4. 글 수정 하기             |");
		System.out.println("     ○------------------------------○");
		System.out.println("     |     5. 글 삭제 하기             |");
		System.out.println("     ○------------------------------○");
		System.out.println("     |     6. 홈으로 돌아가기            |");
		System.out.println("     ○==============================○");
		System.out.print("     메뉴 선택 >>");
	}

	public void run(String id) {
		boolean b = false;
		do {
			boardTitle();
			int key = sc.nextInt();
			sc.nextLine();
			switch (key) {
			case 1:
				boardSelectList();
				break;
			case 2:
				boardSelect();
				break;
			case 3:
				boardAdd(id);
				break;
			case 4:
				boardUpdate(id);
				break;
			case 5:
				boardDelete(id);
				break;
			case 6:
				b = true;
				break;
			}
		} while (!b);
	}

	private void boardSelectList() {
		int page = 1;
		while (true) {
			System.out.println("     ○==============================○");
			System.out.println("     ○==============글목록============○");
			System.out.println("     글번호 : 이름 : 글제목 : 좋아요");
			list(page);
			List<BoardVO> board = dao.boardSelectList();
			int pages = (int) Math.ceil(board.size() / 5.0);
			System.out.print("     ");
			for (int i = 1; i <= pages; i++) {
				if (i == page) {
					System.out.printf(" [" + i + "]");
				} else {
					System.out.printf("  " + i + " ");

				}
			}
			System.out.println();
			System.out.print("     조회페이지 입력(나가기:0 글내용보기: -글번호) >>");
			page = sc.nextInt();
			if (page == 0) {
				break;
			} else if(page<0){
				read(page*-1);
				break;
			}
		}

	}

	public void list(int page) {
		int start = (page - 1) * 5;
		int end = page * 5;
		List<BoardVO> board = dao.boardSelectList();
		for (int i = start; i < board.size(); i++) {
			if (i >= start && i < end) {
				System.out.println("     "+board.get(i).getBoardKey() + " : " + board.get(i).getBoardName() + " : "
						+ board.get(i).getBoardTitle() + " : ♥" + board.get(i).getBoardLike());
			}
		}
	}

	private void boardSelect() {
		System.out.print("     글 번호 >>");
		int key = sc.nextInt();
		sc.nextLine();
		read(key);
	}
	
	private void read(int key) {
		vo.setBoardKey(key);
		vo = dao.boardSelect(vo);
		if (vo != null) {
			System.out.println("     제목 : " + vo.getBoardTitle());
			System.out.println("     이름 : " + vo.getBoardName());
			System.out.println("     날짜 : " + vo.getBoardDate());
			System.out.println("     좋아요 : ♥"+vo.getBoardLike());
			System.out.println("     내용 : " + vo.getBoardContent());
			System.out.println("     --------------------");
			System.out.println("     좋아요 하기 : 1 나가기: 0");
			System.out.print("     ");
			if(sc.nextInt()==1) {
				if(dao.boardLike(vo)!=0) {
					System.out.println("     좋아요가 등록되었습니다.");
				}
			} else {
			}
		} else {
			System.out.println("     존재하지 않는 글 번호 입니다.");
		}
	}

	private void boardAdd(String id) {
		System.out.print("     글 제목 >>");
		String title = sc.nextLine();
		System.out.print("     글 내용 >>");
		String content = sc.nextLine();
		UsersVO usersVO = new UsersVO();
		UsersService usersDao = new UsersServiceImpl();
		usersVO.setUsersId(id);
		usersVO = usersDao.usersSelect(usersVO);
		vo.setBoardId(id);
		vo.setBoardName(usersVO.getUsersName());
		vo.setBoardTitle(title);
		vo.setBoardContent(content);
		if (dao.boardInsert(vo) != 0) {
			System.out.println("     등록되었습니다.");
		}
	}

	private void boardUpdate(String id) {
		System.out.print("     수정할 글 번호 >>");
		int key = sc.nextInt();
		sc.nextLine();
		vo.setBoardKey(key);
		vo = dao.boardSelect(vo);
		if (vo.getBoardId().equals(id)) {
			System.out.print("     글 제목 >>");
			vo.setBoardTitle(sc.nextLine());
			System.out.print("     글 내용 >>");
			vo.setBoardContent(sc.nextLine());
			if (dao.boardUpdate(vo) != 0) {
				System.out.println("     수정이 완료되었습니다.");
			}
			
		} else {
			System.out.println("     권한이 없습니다.");
		}

	}

	private void boardDelete(String id) {
		System.out.print("     삭제할 글 번호>>");
		int key = sc.nextInt();
		sc.nextLine();
		vo.setBoardKey(key);
		vo = dao.boardSelect(vo);
		if (vo.getBoardId().equals(id)) {
			if (dao.boardDelete(vo) != 0) {
				System.out.println("     삭제되었습니다.");
			}
		} else {
			System.out.println("     권한이 없습니다.");
		}
	}
}
