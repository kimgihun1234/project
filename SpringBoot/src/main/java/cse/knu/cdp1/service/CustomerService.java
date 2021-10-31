package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.CustomerDTO;

public interface CustomerService {
    public abstract CustomerDTO getCustomerInfo(String cust_cd);
}
