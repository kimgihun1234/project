package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.CustomerDTO;
import org.mybatis.spring.SqlSessionTemplate;

public interface CustomerDAO {
    public abstract CustomerDTO getCustomerInfo(SqlSessionTemplate session, String cust_cd);
}
