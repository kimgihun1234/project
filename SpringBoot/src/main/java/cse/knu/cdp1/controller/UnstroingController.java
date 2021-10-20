package cse.knu.cdp1.controller;

import cse.knu.cdp1.dto.UnstoringDTO;
import cse.knu.cdp1.dto.UnstoringDetailDTO;
import cse.knu.cdp1.dto.UnstoringTotalDTO;
import cse.knu.cdp1.service.UnstoringDetailService;
import cse.knu.cdp1.service.UnstoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnstroingController {
    @Autowired
    UnstoringService unstoringService;

    @Autowired
    UnstoringDetailService unstoringDetailService;

    @GetMapping("/unstoringList")
    public Map<UnstoringDTO, UnstoringDetailDTO> UnstoringReturn() {
        HashMap<UnstoringDTO, UnstoringDetailDTO> result = new HashMap<>();
        List<UnstoringDTO> unstoringList = unstoringService.unstoringList();
        List<UnstoringDetailDTO> unstoringDetailList = unstoringDetailService.unstoringDetailList();

        for(UnstoringDTO unstoringData : unstoringList) {
            for(UnstoringDetailDTO unstoringDetailData : unstoringDetailList) {
                if(unstoringData.getEx_no().equals(unstoringDetailData.getEx_no())) {
                    result.put(unstoringData, unstoringDetailData);
                }
            }
        }

        return result;
    }

    @GetMapping("/unstoringInsert")
    public void insertUnstoring(@RequestBody UnstoringTotalDTO input) {
        System.out.println(input.getStoringDTO());
        System.out.println(input.getStoringDetailDTO());
        unstoringService.unstoringInsert(input.getStoringDTO());
        unstoringDetailService.unstoringDetailInsert(input.getStoringDetailDTO());
    }

    @GetMapping("/unstoringDelete")
    public void deleteUnstoring(@RequestBody UnstoringTotalDTO input) {
        System.out.println(input.getStoringDTO());
        System.out.println(input.getStoringDetailDTO());
        unstoringService.unstoringDelete(input.getStoringDTO());
        unstoringDetailService.unstoringDetailDelete(input.getStoringDetailDTO());
    }
}
