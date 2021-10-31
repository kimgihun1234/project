package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.OrderDetailDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface OrderDetailDAO {
    public abstract List<OrderDetailDTO> orderDetailList(SqlSessionTemplate session);
}