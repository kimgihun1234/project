package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.BarcodeDTO;
import cse.knu.cdp1.dto.ItemDTO;
import org.mybatis.spring.SqlSessionTemplate;

public interface ItemDAO {
    public abstract ItemDTO getItemInfo(SqlSessionTemplate session, String item_cd);
}
