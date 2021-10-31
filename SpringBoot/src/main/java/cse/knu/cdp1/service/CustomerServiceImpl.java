package cse.knu.cdp1.service;

import cse.knu.cdp1.dao.CustomerDAO;
import cse.knu.cdp1.dto.CustomerDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerDAO dao;

    @Autowired
    SqlSessionTemplate session;

    public CustomerDTO getCustomerInfo(String cust_cd) {
        return dao.getCustomerInfo(session, cust_cd);
    }
}
