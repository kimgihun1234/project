package cse.knu.cdp1.service;

import cse.knu.cdp1.dao.OrderDAO;
import cse.knu.cdp1.dto.OrderDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public List<OrderDTO> orderList() {
        return dao.orderList(session);
    }

    @Override
    public OrderDTO getOrderInfo(String plord_no) {
        return dao.getOrderInfo(session, plord_no);
    }
}
