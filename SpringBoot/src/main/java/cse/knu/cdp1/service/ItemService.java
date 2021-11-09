package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.BarcodeDTO;
import cse.knu.cdp1.dto.ItemDTO;
import cse.knu.cdp1.dto.ItemListDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface ItemService {
    public abstract ItemDTO getItemInfo(String item_cd);
    public abstract List<ItemDTO> itemList();
    public abstract List<ItemListDTO> curItemList();
}
