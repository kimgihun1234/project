package cse.knu.cdp1.controller;

import cse.knu.cdp1.InfoTokenizer;
import cse.knu.cdp1.dto.*;
import cse.knu.cdp1.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
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

    @Autowired
    ItemService itemService;

    @Autowired
    CustomerService customerService;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    LocationService locationService;

    @GetMapping("/storingList")
    public List<StoringListDTO> storingList(@RequestBody String input) {
        HashMap<String, String> searchInfo = new HashMap<>();

        List<String> info = InfoTokenizer.getInfo(input); // 전송된 Data를 저장
        searchInfo.put("cust_cd", info.get(0));
        searchInfo.put("purc_in_dt_1", info.get(1));
        searchInfo.put("purc_in_dt_2", info.get(2));

        // 조건에 맞는 StoringList 생성
        List<StoringListDTO> searchResult = storingService.storingSpecList(searchInfo);

        for(StoringListDTO listTemp : searchResult) {
            searchInfo.put("item_cd", listTemp.getItem_cd());
            listTemp.setPurc_in_dt(storingService.calDate(searchInfo));
            listTemp.setQty(storingService.calTotalSum(searchInfo));
            listTemp.setItem_nm(itemService.getItemInfo(listTemp.getItem_cd()).getItem_nm());
        }

        return searchResult;
    }

    @GetMapping("/storingDetailList")
    public List<StoringListDTO> storingDetailList(@RequestBody String input) {
        HashMap<String, String> searchInfo = new HashMap<>();

        List<String> info = InfoTokenizer.getInfo(input); // 전송된 Data를 저장
        searchInfo.put("cust_cd", info.get(0));
        searchInfo.put("purc_in_dt_1", info.get(1));
        searchInfo.put("purc_in_dt_2", info.get(2));
        searchInfo.put("item_cd", info.get(3));

        // 조건에 맞는 StoringDetailList 생성
        List<StoringListDTO> searchResult = storingDetailService.storingDetailSpecList(searchInfo);

        for(StoringListDTO listTemp : searchResult) {
            listTemp.setItem_nm(itemService.getItemInfo(listTemp.getItem_cd()).getItem_nm());
            listTemp.setCust_nm(customerService.getCustomerInfo(listTemp.getCust_cd()).getCust_nm());
            listTemp.setStor_nm(warehouseService.getWarehouseInfo(listTemp.getStor_cd()).getStor_nm());
            HashMap<String, String> locaInfo = new HashMap<>();
            locaInfo.put("stor_cd", listTemp.getStor_cd());
            locaInfo.put("loca_cd", listTemp.getLoca_cd());
            listTemp.setLoca_nm(locationService.getLocationInfo(locaInfo).getLoca_nm());
        }

        return searchResult;
    }

    /* 발주번호/거래처이름/거래처번호 */
    /* 창고이름/창고번호/위치이름/위치번호 */
    /* ID/사원코드 */
    /* 품목코드/수량 */

    @Alias("storinginputresult")
    @Getter
    @Setter
    @ToString
    public class InputResultClass {
        String purc_in_no;
        String purc_in_dt;
        String item_nm;
        String item_cd;
        Double qty;

        public InputResultClass() {}
    }

    @GetMapping("/storingInsert")
    public InputResultClass insertStoring(@RequestBody String input) {
        StoringDTO storedData; StoringDetailDTO storedDetailData;
        OrderDTO orderTemp = null; OrderDetailDTO orderDetailTemp = null;
        String purc_in_no = null; // 입고 번호 저장용
        InputResultClass result = new InputResultClass(); // 오류 등이 발생했을 경우에는 result에 오류 내용을 담아서 전송
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
            purc_in_no = storingService.purc_in_no_Cal(orderTemp.getCorp_cd()); // 입고 번호 생성

            storedData = new StoringDTO(orderTemp, purc_in_no, info.get(8));
            storedDetailData = new StoringDetailDTO(orderDetailTemp, purc_in_no, info.get(8), info.get(4), info.get(5), info.get(0), info.get(10));

            storingService.storingInsert(storedData);
            storingDetailService.storingDetailInsert(storedDetailData);

            result.purc_in_no = purc_in_no;
        } else { // 이전에 같은 발주 번호로 물건이 입고되었으면, 입고 상세 데이터만 생성하고, 기존 입고 데이터는 갱신.
            purc_in_no = searchResult.get(0).getPurc_in_no();
            storedDetailData = new StoringDetailDTO(orderDetailTemp, purc_in_no, info.get(8), info.get(4), info.get(5), info.get(0), info.get(10));
            storingDetailService.storingDetailInsert(storedDetailData);

            storedData = storingService.storingOne(purc_in_no);
            storingService.storingInsert(storedData);

            result.purc_in_no = purc_in_no;
        }

        result.purc_in_dt = storedData.getPurc_in_dt();
        result.item_cd = storedDetailData.getItem_cd();
        result.item_nm = itemService.getItemInfo(result.item_cd).getItem_nm();
        result.qty = storedDetailData.getQty();
        return result; // 오류가 없었으면 return
    }

    /* 입고번호/품목코드 * n */
    @GetMapping("/storingDelete")
    public boolean deleteStoring(@RequestBody String input) {
        boolean result = false;
        StoringDTO storedData; StoringDetailDTO storedDetailData;
        List<StoringDetailDTO> searchResult;

        // System.out.println(input);

        List<String> info = InfoTokenizer.getInfo(input); // 전송된 Data를 저장

        for(String temp : info) {
            // System.out.println(temp);
        }

        for(int i = 0; i < info.size(); i+=2) { // 들어온 String의 갯수만큼 반복해서 처리
            searchResult = storingDetailService.checkDetailList(info.get(i)); // 이미 해당 입고 번호로 저장된 물건이 있는지 확인
            if(searchResult.size() > 1) { // 입고된 자재가 1개 이상이면 StoringDetail 정보만 제거

                storedDetailData = new StoringDetailDTO(info.get(i), info.get(i + 1));
                storingDetailService.storingDetailDelete(storedDetailData);

                storedData = storingService.storingOne(info.get(i));
                storingService.storingInsert(storedData);
                result = true;
            } else { // 입고된 자재가 1개 뿐이면 StoringDetail, Storing 둘 다 제거

                storedDetailData = new StoringDetailDTO(info.get(i), info.get(i + 1));
                storingDetailService.storingDetailDelete(storedDetailData);

                storedData = storingService.storingOne(info.get(i));
                storingService.storingDelete(storedData);
                result = true;
            }
        }

        return result;
    }
}