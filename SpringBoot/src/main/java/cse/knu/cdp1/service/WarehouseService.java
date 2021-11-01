package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.WarehouseDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface WarehouseService {
    public abstract List<WarehouseDTO> warehouseList();
    public abstract WarehouseDTO getWarehouseInfo(String stor_cd);
}
