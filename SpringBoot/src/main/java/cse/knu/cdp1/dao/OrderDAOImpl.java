package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.OrderDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO{
    @Override
    public List<OrderDTO> orderList(SqlSessionTemplate session) { return session.selectList("orderMapper.orderList"); }

    @Override
    public OrderDTO getOrderInfo(SqlSessionTemplate session, String plord_no) {
        return session.selectOne("orderMapper.getOrderInfo", plord_no);
    }
}
