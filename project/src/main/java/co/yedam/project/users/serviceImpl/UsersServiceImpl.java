package co.yedam.project.users.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.project.common.DataSource;
import co.yedam.project.users.map.UsersMapper;
import co.yedam.project.users.service.UsersService;
import co.yedam.project.users.service.UsersVO;

public class UsersServiceImpl implements UsersService{
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private UsersMapper map = sqlSession.getMapper(UsersMapper.class);
	
	@Override
	public List<UsersVO> usersSelectList() {
		return map.usersSelectList();
	}

	@Override
	public UsersVO usersSelect(UsersVO vo) {
		return map.usersSelect(vo);
	}

	@Override
	public int usersInsert(UsersVO vo) {
		return map.usersInsert(vo);
	}

	@Override
	public int usersUpdate(UsersVO vo) {
		return map.usersUpdate(vo);
	}

	@Override
	public int usersDelete(UsersVO vo) {
		return map.usersDelete(vo);
	}

}
