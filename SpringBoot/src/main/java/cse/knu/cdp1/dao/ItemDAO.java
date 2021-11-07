package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.ItemDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface ItemDAO {
    public abstract ItemDTO getItemInfo(SqlSessionTemplate session, String item_cd);
    public abstract List<ItemDTO> itemList(SqlSessionTemplate session);
}
