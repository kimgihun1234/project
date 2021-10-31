package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.OrderDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface OrderService {
    public abstract List<OrderDTO> orderList();
    public abstract OrderDTO getOrderInfo(String plord_no);
}
