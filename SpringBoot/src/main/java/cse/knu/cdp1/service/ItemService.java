package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.BarcodeDTO;
import cse.knu.cdp1.dto.ItemDTO;

public interface ItemService {
    public abstract ItemDTO getItemInfo(String item_cd);
}
