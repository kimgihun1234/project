package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.CustomerDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface CustomerDAO {
    public abstract List<CustomerDTO> customerList(SqlSessionTemplate session);
    public abstract CustomerDTO getCustomerInfo(SqlSessionTemplate session, String cust_cd);
}
