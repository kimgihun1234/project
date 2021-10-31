package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.CustomerDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
    @Override
    public CustomerDTO getCustomerInfo(SqlSessionTemplate session, String cust_cd) {
        return session.selectOne("mapper.getCustomerInfo", cust_cd);
    }
}
