package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.ItemDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDAOImpl implements ItemDAO{
    @Override
    public ItemDTO getItemInfo(SqlSessionTemplate session, String item_cd) {
        return session.selectOne("mapper.getItemInfo", item_cd);
    }
}
