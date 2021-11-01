package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.WarehouseDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface WarehouseDAO {
    public abstract List<WarehouseDTO> warehouseList(SqlSessionTemplate session);
    public abstract WarehouseDTO getWarehouseInfo(SqlSessionTemplate session, String stor_cd);
}
