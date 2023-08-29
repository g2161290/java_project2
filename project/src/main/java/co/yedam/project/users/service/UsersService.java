package co.yedam.project.users.service;

import java.util.List;

public interface UsersService {
	List<UsersVO> usersSelectList();
	UsersVO usersSelect(UsersVO vo);
	int usersInsert(UsersVO vo);
	int usersUpdate(UsersVO vo);
	int usersDelete(UsersVO vo);
}
