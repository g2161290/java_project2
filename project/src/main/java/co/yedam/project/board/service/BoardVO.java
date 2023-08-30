package co.yedam.project.board.service;

import java.time.LocalDate;

import lombok.Data;
@Data
public class BoardVO {
	private int boardKey;
	private String boardId;
	private String boardName;
	private String boardTitle;
	private String boardContent;
	private LocalDate boardDate;
	private int boardLike;
}
