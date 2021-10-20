package cse.knu.cdp1.controller;

import cse.knu.cdp1.dto.StoringDTO;
import cse.knu.cdp1.dto.StoringDetailDTO;
import cse.knu.cdp1.dto.StoringTotalDTO;
import cse.knu.cdp1.service.StoringDetailService;
import cse.knu.cdp1.service.StoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StoringController {
    @Autowired
    StoringService storingService;

    @Autowired
    StoringDetailService storingDetailService;

    @GetMapping("/storingList")
    public Map<StoringDTO, StoringDetailDTO> StoringReturn() {
        HashMap<StoringDTO, StoringDetailDTO> result = new HashMap<>();
        List<StoringDTO> storingList = storingService.storingList();
        List<StoringDetailDTO> storingDetailList = storingDetailService.storingDetailList();

        for(StoringDTO storingData : storingList) {
            for(StoringDetailDTO storingDetailData : storingDetailList) {
                if(storingData.getPurc_in_no().equals(storingDetailData.getPurc_in_no())) {
                    result.put(storingData, storingDetailData);
                }
            }
        }

        return result;
    }

    @GetMapping("/storingInsert")
    public void insertStoring(@RequestBody StoringTotalDTO input) {
        System.out.println(input.getStoringDTO());
        System.out.println(input.getStoringDetailDTO());
        storingService.storingInsert(input.getStoringDTO());
        storingDetailService.storingDetailInsert(input.getStoringDetailDTO());
    }

    @GetMapping("/storingDelete")
    public void deleteStoring(@RequestBody StoringTotalDTO input) {
        System.out.println(input.getStoringDTO());
        System.out.println(input.getStoringDetailDTO());
        storingService.storingDelete(input.getStoringDTO());
        storingDetailService.storingDetailDelete(input.getStoringDetailDTO());
    }
}
