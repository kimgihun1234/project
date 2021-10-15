package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.UserDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{
    @Override
    public List<UserDTO> getLoginInfo(SqlSessionTemplate session, UserDTO searchData) {
        return session.selectList("mapper.userInfo", searchData);
    }
}
