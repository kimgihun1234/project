package cse.knu.cdp1.service;

import cse.knu.cdp1.dao.OrderDetailDAO;
import cse.knu.cdp1.dto.OrderDetailDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public List<OrderDetailDTO> orderDetailList() {
        return dao.orderDetailList(session);
    }
}
