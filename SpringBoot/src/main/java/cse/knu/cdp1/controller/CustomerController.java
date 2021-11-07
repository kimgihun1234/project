package cse.knu.cdp1.controller;

import cse.knu.cdp1.dto.CustomerDTO;
import cse.knu.cdp1.service.CustomerService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {
    @Alias("customerresult")
    @Getter
    @Setter
    @ToString
    public class ResultClass {
        String cust_cd;
        String cust_nm;
    }

    @Autowired
    CustomerService customerService;

    @GetMapping("/customerList")
    public List<ResultClass> customerListReturn() {
        List<ResultClass> result = new ArrayList<>();

        List<CustomerDTO> search = customerService.customerList();

        for(CustomerDTO data : search) {
            ResultClass temp = new ResultClass();
            temp.cust_cd = data.getCust_cd();
            temp.cust_nm = data.getCust_nm();
            result.add(temp);
        }

        return result;
    }
}
