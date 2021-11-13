package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.WarehouseDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WarehouseDAOImpl implements WarehouseDAO{
    @Override
    public List<WarehouseDTO> warehouseList(SqlSessionTemplate session) {
        return session.selectList("locationMapper.warehouseList");
    }

    @Override
    public WarehouseDTO getWarehouseInfo(SqlSessionTemplate session, String stor_cd) {
        return session.selectOne("locationMapper.getWarehouseInfo", stor_cd);
    }
}
