package cse.knu.cdp1.controller;

import cse.knu.cdp1.dto.CustomerDTO;
import cse.knu.cdp1.dto.OrderDTO;
import cse.knu.cdp1.dto.OrderDetailDTO;
import cse.knu.cdp1.service.CustomerService;
import cse.knu.cdp1.service.OrderDetailService;
import cse.knu.cdp1.service.OrderService;
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
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    CustomerService customerService;

    @Alias("orderresult")
    @Getter
    @Setter
    @ToString
    public class ResultClass {
        String plord_no;
        String cust_nm;
        String cust_cd;
    }

    @GetMapping("/orderList")
    public List<ResultClass> orderReturn() {
        ArrayList<ResultClass> result = new ArrayList<>();
        CustomerDTO customerInfo;

        List<OrderDTO> orderList = orderService.orderList();

        for(OrderDTO orderData : orderList) {
            customerInfo = customerService.getCustomerInfo(orderData.getCust_cd());
            System.out.println(orderData.getPlord_no() + "/" + customerInfo.getCust_nm() + "/" + orderData.getCust_cd());
            ResultClass temp = new ResultClass();
            temp.plord_no = orderData.getPlord_no();
            temp.cust_nm = customerInfo.getCust_nm();
            temp.cust_cd = orderData.getCust_cd();
            result.add(temp);
        }

        /* 발주번호/거래처이름/거래처번호 */
        return result;
    }
}
