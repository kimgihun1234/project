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
        String purc_in_no;
        List<StoringDetailDTO> searchResult;

        // System.out.println(input);

        List<String> info = InfoTokenizer.getInfo(input); // 전송된 Data를 저장

        for(String temp : info) {
            // System.out.println(temp);
        }

        orderTemp = orderService.getOrderInfo(info.get(0)); // 필요한 발주 정보를 받아 옴

        List<OrderDetailDTO> orderDetailList = orderDetailService.orderDetailList();
        // 하나의 발주 번호에 여러 개의 품목이 담겨져 있음 => 저장하고자 하는 물건의 품목 코드, 발주 번호와 일치하는 발주 상세 정보를 갖고 옴
        for(OrderDetailDTO temp : orderDetailList) {
            if(orderTemp.getPlord_no().equals(temp.getPlord_no()) && info.get(9).equals(temp.getItem_cd())) {
                orderDetailTemp = temp;
                break;
            }
        }

        searchResult = storingDetailService.checkFormerDetailList(orderTemp.getPlord_no()); // 이미 해당 발주 번호로 저장된 물건이 있는지 확인
        if(searchResult.isEmpty()) { // 발주 번호로 저장된 물건이 없으면 새로 입고와 입고 상세 데이터를 생성
            purc_in_no = storingService.purc_in_no_Cal(orderTemp.getCorp_cd());

            storedData = new StoringDTO(orderTemp, purc_in_no, info.get(8));
            storedDetailData = new StoringDetailDTO(orderDetailTemp, purc_in_no, info.get(8), info.get(4), info.get(5), info.get(0), info.get(10));

            storingService.storingInsert(storedData);
            storingDetailService.storingDetailInsert(storedDetailData);
        } else { // 이전에 같은 발주 번호로 물건이 입고되었으면, 입고 상세 데이터만 생성
            purc_in_no = searchResult.get(0).getPurc_in_no();

            storedDetailData = new StoringDetailDTO(orderDetailTemp, purc_in_no, info.get(8), info.get(4), info.get(5), info.get(0), info.get(10));
            storingDetailService.storingDetailInsert(storedDetailData);
        }
    }

    @GetMapping("/storingDelete")
    public void deleteStoring(@RequestBody String input) {

    }
}