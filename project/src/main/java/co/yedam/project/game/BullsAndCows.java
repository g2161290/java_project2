package co.yedam.project.game;

import java.time.LocalDate;
import java.util.Scanner;

import co.yedam.project.users.service.UsersService;
import co.yedam.project.users.service.UsersVO;
import co.yedam.project.users.serviceImpl.UsersServiceImpl;

public class BullsAndCows {
	Scanner sc = new Scanner(System.in);
	int[] randomNumbers = new int[3];
	int life;
	int score;

	public void run(String id) {
		life = 5;
		score = 0;
		play();
		save(id);
	}

	private void play() {
		boolean b = false;
		boolean a= false;
		while (!b) {
			randomNumbers();
			while (!a) {
				int strike = 0;
				int ball = 0;
				System.out.println("     중복되지 않는 숫자 세 개(0~9)를 입력하세요.");
				System.out.println("     나가기 : -1");
				life(life);
				int numbers[] = new int[3];
				for (int i = 0; i < numbers.length; i++) {
					System.out.print("     ");
					numbers[i] = sc.nextInt();
					if(numbers[i]==-1) {
						System.out.println("     점수: " + score);
						a=true;
						b=true;
						break;
					}
					if (randomNumbers[i] == numbers[i]) {
						strike += 1;
					} else {
						for (int j = 0; j < randomNumbers.length; j++) {
							if (numbers[i] == randomNumbers[j]) {
								ball += 1;
							}
						}
					}
				}
				if (strike == 3) {
					System.out.println("     정답입니다.");
					score += life;
					System.out.println("     점수: " + score);
					life = 5;
					break;
				} else {
					score(strike, ball);
					life -= 1;
				}
				if (life == 0) {
					System.out.println("     횟수를 초과하여 종료합니다.");
					System.out.println(
							"     정답은 " + randomNumbers[0] + ", " + randomNumbers[1] + ", " + randomNumbers[2] + ", ");
					System.out.println("     점수: " + score);
					b = true;
					break;
				}

			}
		}
	}

	private int[] randomNumbers() {
		for (int i = 0; i < randomNumbers.length; i++) {
			randomNumbers[i] = (int) (Math.random() * 10);
			for (int j = 0; j < i; j++) {
				if (randomNumbers[j] == randomNumbers[i]) {
					i -= 1;
				}
			}
		}
		return randomNumbers;
	}

	private void score(int strike, int ball) {
		System.out.print("     "+strike + "S ");
		System.out.print(ball + "B ");
		System.out.println((randomNumbers.length - strike - ball) + "O (S:위치O숫자O B:위치X숫자O O:위치X숫자X)");
	}

	private void life(int life) {
		System.out.print("     ");
		for (int i = 0; i < life; i++) {
			System.out.print("♥");
		}
		for (int i = 0; i < 5 - life; i++) {
			System.out.print("♡");
		}
		System.out.println();
	}

	private void save(String id) {
		UsersVO vo = new UsersVO();
		UsersService dao = new UsersServiceImpl();
		vo.setUsersId(id);
		UsersVO voOrigin = dao.usersSelect(vo);
		if (voOrigin.getUsersBullsandcowsScore() < score) {
			vo.setUsersBullsandcowsScore(score);
			vo.setUsersBullsandcowsScoreDate(LocalDate.now());
			dao.usersUpdate(vo);
		}
	}
}
