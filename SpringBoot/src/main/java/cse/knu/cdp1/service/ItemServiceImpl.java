package cse.knu.cdp1.service;

import cse.knu.cdp1.dao.BarcodeDAO;
import cse.knu.cdp1.dao.ItemDAO;
import cse.knu.cdp1.dto.ItemDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public ItemDTO getItemInfo(String item_cd) {
        return dao.getItemInfo(session, item_cd);
    }

    @Override
    public List<ItemDTO> itemList() {
        return dao.itemList(session);
    }
}
