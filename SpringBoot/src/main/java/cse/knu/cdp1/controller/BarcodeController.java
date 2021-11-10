package cse.knu.cdp1.controller;

import cse.knu.cdp1.BarcodeInfo;
import cse.knu.cdp1.dto.BarcodeDTO;
import cse.knu.cdp1.dto.ItemDTO;
import cse.knu.cdp1.service.BarcodeService;
import cse.knu.cdp1.service.ItemService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/barcode")
    public ResultClass itemInfo(@RequestBody BarcodeInfo input) {
        BarcodeDTO result = barcodeService.getBarcodeInfo(input.getBarcode());
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
