package cse.knu.cdp1.controller;

import cse.knu.cdp1.InfoTokenizer;
import cse.knu.cdp1.dto.*;
import cse.knu.cdp1.service.OrderDetailService;
import cse.knu.cdp1.service.OrderService;
import cse.knu.cdp1.service.StoringDetailService;
import cse.knu.cdp1.service.StoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class StoringController {
    @Autowired
    StoringService storingService;

    @Autowired
    StoringDetailService storingDetailService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;
    private Object OrderDetailDTO;

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

    /* 발주번호/거래처이름/거래처번호 */
    /* 창고이름/창고번호/위치이름/위치번호 */
    /* ID/사원코드 */
    /* 품목코드/수량 */

    @GetMapping("/storingInsert")
    public void insertStoring(@RequestBody String input) {
        StoringDTO storedData; StoringDetailDTO storedDetailData;
        OrderDTO orderTemp = null; OrderDetailDTO orderDetailTemp = null;

        // System.out.println(input);

        List<String> info = InfoTokenizer.getInfo(input);

        for(String temp : info) {
            // System.out.println(temp);
        }

        orderTemp = orderService.getOrderInfo(info.get(0));

        List<OrderDetailDTO> orderDetailList = orderDetailService.orderDetailList();
        for(OrderDetailDTO temp : orderDetailList) {
            if(orderTemp.getPlord_no().equals(temp.getPlord_no()) && info.get(9).equals(temp.getItem_cd())) {
                orderDetailTemp = temp;
                break;
            }
        }

        storedData = new StoringDTO(orderTemp, info.get(8));

        storedDetailData = new StoringDetailDTO(orderDetailTemp, info.get(8), info.get(4), info.get(5), info.get(0), info.get(10));
        storingService.storingInsert(storedData);
        storingDetailService.storingDetailInsert(storedDetailData);
    }

    @GetMapping("/storingDelete")
    public void deleteStoring(@RequestBody String input) {

    }
}
