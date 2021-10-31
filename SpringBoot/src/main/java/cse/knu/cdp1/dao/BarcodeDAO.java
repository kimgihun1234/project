package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.BarcodeDTO;
import org.mybatis.spring.SqlSessionTemplate;

public interface BarcodeDAO {
    public abstract BarcodeDTO getBarcodeInfo(SqlSessionTemplate session, String barcode);
}
