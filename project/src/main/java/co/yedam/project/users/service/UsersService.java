package co.yedam.project.users.service;

import java.util.List;

public interface UsersService {
	List<UsersVO> usersNumberquizSelectList();
	List<UsersVO> usersBullsandcowsSelectList();
	UsersVO usersSelect(UsersVO vo);
	int usersInsert(UsersVO vo);
	int usersUpdate(UsersVO vo);
	int usersDelete(UsersVO vo);
}
