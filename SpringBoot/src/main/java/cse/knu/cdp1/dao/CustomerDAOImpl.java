package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.CustomerDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
    @Override
    public List<CustomerDTO> customerList(SqlSessionTemplate session) {
        return session.selectList("mapper.customerList");
    }
    @Override
    public CustomerDTO getCustomerInfo(SqlSessionTemplate session, String cust_cd) {
        return session.selectOne("mapper.getCustomerInfo", cust_cd);
    }
}
