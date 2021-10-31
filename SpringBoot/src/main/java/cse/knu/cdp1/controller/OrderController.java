package cse.knu.cdp1.controller;

import cse.knu.cdp1.dto.CustomerDTO;
import cse.knu.cdp1.dto.OrderDTO;
import cse.knu.cdp1.dto.OrderDetailDTO;
import cse.knu.cdp1.service.CustomerService;
import cse.knu.cdp1.service.OrderDetailService;
import cse.knu.cdp1.service.OrderService;
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

    @GetMapping("/orderList")
    public List<String> orderReturn() {
        ArrayList<String> result = new ArrayList<>();
        CustomerDTO customerInfo;

        List<OrderDTO> orderList = orderService.orderList();

        for(OrderDTO orderData : orderList) {
            customerInfo = customerService.getCustomerInfo(orderData.getCust_cd());
            System.out.println(orderData.getPlord_no() + "/" + customerInfo.getCust_nm() + "/" + orderData.getCust_cd());
            result.add(orderData.getPlord_no() + "/" + customerInfo.getCust_nm() + "/" + orderData.getCust_cd());
        }

        /* 발주번호/거래처이름/거래처번호 */
        return result;
    }
}
