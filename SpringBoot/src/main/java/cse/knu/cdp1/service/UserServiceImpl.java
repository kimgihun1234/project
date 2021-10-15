package cse.knu.cdp1.service;

import cse.knu.cdp1.dao.UserDAO;
import cse.knu.cdp1.dto.UserDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDAO dao;

    @Autowired
    SqlSessionTemplate session;
    public List<UserDTO> getLoginInfo(UserDTO searchData) {
        return dao.getLoginInfo(session, searchData);
    }
}
