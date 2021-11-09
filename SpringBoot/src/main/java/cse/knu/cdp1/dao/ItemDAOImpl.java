package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.ItemDTO;
import cse.knu.cdp1.dto.ItemListDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDAOImpl implements ItemDAO {
    @Override
    public ItemDTO getItemInfo(SqlSessionTemplate session, String item_cd) {
        return session.selectOne("mapper.getItemInfo", item_cd);
    }

    @Override
    public List<ItemDTO> itemList(SqlSessionTemplate session) {
        return session.selectList("mapper.itemList");
    }

    @Override
    public List<ItemListDTO> curItemList(SqlSessionTemplate session) {
        return session.selectList("mapper.curItemList");
    }
}
