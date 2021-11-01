package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.CustomerDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface CustomerService {
    public abstract List<CustomerDTO> customerList();
    public abstract CustomerDTO getCustomerInfo(String cust_cd);
}
