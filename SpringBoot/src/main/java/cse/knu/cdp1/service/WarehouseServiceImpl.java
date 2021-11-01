package cse.knu.cdp1.service;

import cse.knu.cdp1.dao.WarehouseDAO;
import cse.knu.cdp1.dto.WarehouseDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService{
    @Autowired
    WarehouseDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public List<WarehouseDTO> warehouseList() {
        return dao.warehouseList(session);
    }

    @Override
    public WarehouseDTO getWarehouseInfo(String stor_cd) {
        return dao.getWarehouseInfo(session, stor_cd);
    }
}
