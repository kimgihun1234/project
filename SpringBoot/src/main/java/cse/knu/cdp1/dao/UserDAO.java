package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.UserDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface UserDAO {
    public abstract List<UserDTO> getLoginInfo(SqlSessionTemplate session, UserDTO searchData);
}

