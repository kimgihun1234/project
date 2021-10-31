package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.BarcodeDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BarcodeDAOImpl implements BarcodeDAO{
    @Override
    public BarcodeDTO getBarcodeInfo(SqlSessionTemplate session, String barcode) {
        return session.selectOne("mapper.getBarcodeInfo", barcode);
    }
}
