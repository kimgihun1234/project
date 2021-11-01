package cse.knu.cdp1.controller;

import cse.knu.cdp1.dto.BarcodeDTO;
import cse.knu.cdp1.dto.ItemDTO;
import cse.knu.cdp1.service.BarcodeService;
import cse.knu.cdp1.service.ItemService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BarcodeController {
    @Alias("barcoderesult")
    @Getter
    @Setter
    @ToString
    public class ResultClass {
        String item_nm;
        String item_cd;
        Double qty;

        public ResultClass() {}
    }

    @Autowired
    BarcodeService barcodeService;

    @Autowired
    ItemService itemService;

    @GetMapping("/barcode")
    public ResultClass itemInfo(@RequestBody String barcode) {
        BarcodeDTO result = barcodeService.getBarcodeInfo(barcode);
        ItemDTO item;

        if(result == null) return null;

        item = itemService.getItemInfo(result.getItem_cd());

        ResultClass temp = new ResultClass();
        temp.item_cd = result.getItem_cd();
        temp.item_nm = item.getItem_nm();
        temp.qty = result.getQty();
        /* 품목번호/수량/품목명 */
        return temp;
    }
}
