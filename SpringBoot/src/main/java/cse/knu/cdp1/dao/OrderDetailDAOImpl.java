package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.OrderDetailDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDetailDAOImpl implements OrderDetailDAO{
    @Override
    public List<OrderDetailDTO> orderDetailList(SqlSessionTemplate session) { return session.selectList("orderMapper.orderDetailList"); }
}
