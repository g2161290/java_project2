package co.yedam.project.users.map;

import java.util.List;

import co.yedam.project.users.service.UsersVO;

public interface UsersMapper {
	List<UsersVO> usersSelectList();
	UsersVO usersSelect(UsersVO vo);
	int usersInsert(UsersVO vo);
	int usersUpdate(UsersVO vo);
	int usersDelete(UsersVO vo);
}
