package cse.knu.cdp1.controller;

import cse.knu.cdp1.dto.CustomerDTO;
import cse.knu.cdp1.dto.OfferDTO;
import cse.knu.cdp1.service.CustomerService;
import cse.knu.cdp1.service.OfferService;
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
public class OfferController {
    @Autowired
    OfferService offerService;

    @Autowired
    CustomerService customerService;

    @Alias("offerresult")
    @Getter
    @Setter
    @ToString
    public class ResultClass {
        String ex_requ_no;
        String cust_nm;
        String cust_cd;
    }

    @GetMapping("/offerList")
    public List<ResultClass> offerReturn() {
        ArrayList<ResultClass> result = new ArrayList<>();
        CustomerDTO customerInfo;

        List<OfferDTO> offerList = offerService.offerList();

        for(OfferDTO offerData : offerList) {
            customerInfo = customerService.getCustomerInfo(offerData.getCust_cd());
            ResultClass temp = new ResultClass();
            temp.ex_requ_no = offerData.getEx_requ_no();
            temp.cust_nm = customerInfo.getCust_nm();
            temp.cust_cd = offerData.getCust_cd();
            result.add(temp);
        }

        /* 출고요청번호/거래처이름/거래처번호 */
        return result;
    }

}
