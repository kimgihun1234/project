package cse.knu.cdp1.controller;

import cse.knu.cdp1.dto.BarcodeDTO;
import cse.knu.cdp1.dto.ItemDTO;
import cse.knu.cdp1.dto.UserDTO;
import cse.knu.cdp1.service.BarcodeService;
import cse.knu.cdp1.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BarcodeController {
    @Autowired
    BarcodeService barcodeService;

    @Autowired
    ItemService itemService;

    @GetMapping("/barcode")
    public String itemInfo(@RequestBody String barcode) {
        BarcodeDTO result = barcodeService.getBarcodeInfo(barcode);
        ItemDTO item;

        if(result == null) return "false";

        item = itemService.getItemInfo(result.getItem_cd());

        /* 품목번호/수량/품목명 */
        return result.getInfo() + "/" + item.getItem_nm();
    }
}
