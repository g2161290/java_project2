package co.yedam.test.member.service;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MemberVO {
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberTel;
	private LocalDate memberEnterDate;
}
