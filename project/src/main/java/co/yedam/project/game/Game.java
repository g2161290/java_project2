package co.yedam.project.game;

import java.time.LocalDate;
import java.util.Scanner;

import co.yedam.project.users.service.UsersService;
import co.yedam.project.users.service.UsersVO;
import co.yedam.project.users.serviceImpl.UsersServiceImpl;

public class Game {
	Scanner sc= new Scanner(System.in);
	int difficulty;
	int life;
	Integer score;
	
	
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
			System.out.println("     1에서 "+difficulty*10+"까지의 수 중에서 특정 수를 맞춰보세요");
			System.out.println("     나가기: -1");
			life(life);
			System.out.print("     ");
			int n=sc.nextInt();
			int randomNumber=(int) (Math.random()*difficulty*10)+1;
			while(true) {
				if(n==-1) {
					System.out.println("     점수 : "+score);
					b=true;
					break;
				} 
				if(randomNumber>n) {
					System.out.println("     더 높은 수 입니다.");
					life-=1;
					life(life);
					System.out.print("     ");
				} else if(randomNumber<n){
					System.out.println("     더 낮은 수 입니다.");
					life-=1;
					life(life);
					System.out.print("     ");
				} else {
					System.out.println("     맞았습니다!");
					score+=life*difficulty;
					life=5;
					difficulty+=1;
					life(life);
					System.out.println("     점수: " + score);
					break;
				}
				if(life==0) {
					System.out.println("life가 0이 되어 게임을 종료합니다.");
					System.out.println("     점수 : "+score);
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
		Integer n=voOrigin.getUsersNumberquizScore();
		if(n<score) {
			vo.setUsersNumberquizScore(score);
			vo.setUsersNumberquizScoreDate(LocalDate.now());
			dao.usersUpdate(vo);
		}
		
	}
	
	private void life(int life) {
		System.out.print("     ");
		for(int i=0; i<life; i++) {
			System.out.print("♥");
		}
		for(int i=0; i<5-life; i++) {
			System.out.print("♡");
		}
		System.out.println();
	}
}
