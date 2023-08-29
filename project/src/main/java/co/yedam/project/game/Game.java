package co.yedam.project.game;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

import co.yedam.project.users.service.UsersService;
import co.yedam.project.users.service.UsersVO;
import co.yedam.project.users.serviceImpl.UsersServiceImpl;

public class Game {
	Scanner sc= new Scanner(System.in);
	int difficulty;
	int life;
	int score;
	
	
	public void run(String id) {
		difficulty=1;
		life=5;
		score=0;
		play();
		save(id);
	}
	private void play() {
		boolean b=false;
		while(!b) {
			System.out.println("1에서 "+difficulty*10+"까지의 수 중에서 특정 수를 맞춰보세요");
			life(life);
			int n=sc.nextInt();
			if(n==-1) {
				break;
			} 
			int randomNumber=(int) (Math.random()*difficulty*10)+1;
			while(true) {
				if(randomNumber>n) {
					System.out.println("더 높은 수 입니다.");
					life-=1;
					life(life);
				} else if(randomNumber<n){
					System.out.println("더 낮은 수 입니다.");
					life-=1;
					life(life);
				} else {
					System.out.println("맞았습니다!");
					score+=life*difficulty;
					life=5;
					difficulty+=1;
					life(life);
					break;
				}
				if(life==0) {
					System.out.println("life가 0이 되어 게임을 종료합니다.");
					System.out.println("점수 : "+score);
					b=true;
					break;
				}
				n=sc.nextInt();
			}
		}
	}
	
	private void save(String id) {
		UsersVO vo=new UsersVO();
		UsersService dao=new UsersServiceImpl();
		vo.setUsersId(id);
		UsersVO voOrigin=dao.usersSelect(vo);
		if(voOrigin.getUsersScore()<score) {
			vo.setUsersScore(score);
			vo.setUsersScoreDate(LocalDate.now());
			dao.usersUpdate(vo);
		}
	}
	
	private void life(int life) {
		for(int i=0; i<life; i++) {
			System.out.print("♥");
		}
		for(int i=0; i<5-life; i++) {
			System.out.print("♡");
		}
		System.out.println();
	}
}
