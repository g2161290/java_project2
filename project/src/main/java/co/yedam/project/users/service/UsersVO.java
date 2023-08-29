package co.yedam.project.users.service;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UsersVO {
	private String usersId;
	private String usersPassword;
	private String usersName;
	private int usersScore;
	private LocalDate usersScoreDate;
}
