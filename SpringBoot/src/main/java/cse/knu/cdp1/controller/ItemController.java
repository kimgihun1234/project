package cse.knu.cdp1.controller;

import cse.knu.cdp1.dto.ItemDTO;
import cse.knu.cdp1.dto.ItemListDTO;
import cse.knu.cdp1.service.ItemService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {
    @Autowired
    ItemService itemService;

    @Alias("itemresult")
    @Getter
    @Setter
    @ToString
    public class ResultClass {
        String item_cd;
        String item_nm;
    }


    @GetMapping("/itemList")
    public List<ResultClass> itemListReturn() {
        ArrayList<ResultClass> result = new ArrayList<>();
        List<ItemDTO> itemList = itemService.itemList();

        for(ItemDTO itemData : itemList) {
            ResultClass temp = new ResultClass();
            temp.item_cd = itemData.getItem_cd();
            temp.item_nm = itemData.getItem_nm();
            result.add(temp);
        }

        /* item_cd/item_nm */
        return result;
    }

    @GetMapping("/curItemList")
    public List<ItemListDTO> curItemListReturn() {
        return itemService.curItemList();
    }
}
