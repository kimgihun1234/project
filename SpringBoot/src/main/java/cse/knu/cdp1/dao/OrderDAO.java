package cse.knu.cdp1.dao;


import cse.knu.cdp1.dto.OrderDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface OrderDAO {
    public abstract List<OrderDTO> orderList(SqlSessionTemplate session);
    public abstract OrderDTO getOrderInfo(SqlSessionTemplate session, String plord_no);
}
