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

import java.util.HashMap;
import java.util.List;

@RestController
public class StoringReturnController {
    @Autowired
    StoringReturnService storingReturnService;

    @Autowired
    StoringReturnDetailService storingReturnDetailService;

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

    @GetMapping("/storingReturnList")
    public List<StoringReturnListDTO> storingReturnList(@RequestBody String input) {
        HashMap<String, String> searchInfo = new HashMap<>();

        List<String> info = InfoTokenizer.getInfo(input); // 전송된 Data를 저장
        searchInfo.put("cust_cd", info.get(0));
        searchInfo.put("purc_retu_dt_1", info.get(1));
        searchInfo.put("purc_retu_dt_2", info.get(2));

        // 조건에 맞는 StoringReturnList 생성
        List<StoringReturnListDTO> searchResult = storingReturnService.storingReturnSpecList(searchInfo);

        for(StoringReturnListDTO listTemp : searchResult) {
            searchInfo.put("item_cd", listTemp.getItem_cd());
            listTemp.setItem_nm(itemService.getItemInfo(listTemp.getItem_cd()).getItem_nm());
        }

        return searchResult;
    }

    @GetMapping("/storingReturnDetailList")
    public List<StoringReturnListDTO> storingReturnDetailList(@RequestBody String input) {
        HashMap<String, String> searchInfo = new HashMap<>();

        List<String> info = InfoTokenizer.getInfo(input); // 전송된 Data를 저장
        searchInfo.put("cust_cd", info.get(0));
        searchInfo.put("purc_retu_dt_1", info.get(1));
        searchInfo.put("purc_retu_dt_2", info.get(2));
        searchInfo.put("item_cd", info.get(3));

        // 조건에 맞는 StoringReturnDetailList 생성
        List<StoringReturnListDTO> searchResult = storingReturnDetailService.storingReturnDetailSpecList(searchInfo);

        for(StoringReturnListDTO listTemp : searchResult) {
            // 아이템 이름 삽입
            if(listTemp.getItem_cd() == null) listTemp.setItem_nm("null");
            else listTemp.setItem_nm(itemService.getItemInfo(listTemp.getItem_cd()).getItem_nm());

            // 거래처 이름 삽입
            if(listTemp.getCust_cd() == null) listTemp.setCust_nm("null");
            else listTemp.setCust_nm(customerService.getCustomerInfo(listTemp.getCust_cd()).getCust_nm());

            // 창고 이름 삽입
            if(listTemp.getStor_cd() == null) listTemp.setStor_nm("null");
            else listTemp.setStor_nm(warehouseService.getWarehouseInfo(listTemp.getStor_cd()).getStor_nm());

            // 위치 정보 Handling
            if(listTemp.getLoca_cd() == null) listTemp.setLoca_nm("null");
            else {
                HashMap<String, String> locaInfo = new HashMap<>(); // 위치 정보 검색
                locaInfo.put("stor_cd", listTemp.getStor_cd());
                locaInfo.put("loca_cd", listTemp.getLoca_cd());
                listTemp.setLoca_nm(locationService.getLocationInfo(locaInfo).getLoca_nm()); // 위치 이름 삽입
            }
        }

        return searchResult;
    }

    /* 발주번호/거래처이름/거래처번호 */
    /* 창고이름/창고번호/위치이름/위치번호 */
    /* ID/사원코드 */
    /* 품목코드/수량 */
    @Alias("storingreturninputresult")
    @Getter
    @Setter
    @ToString
    public class InputResultClass {
        String purc_retu_no;
        String purc_retu_dt;
        String item_nm;
        String item_cd;
        Double qty;

        public InputResultClass() {}
    }

    @GetMapping("/storingReturnInsert")
    public InputResultClass insertStoringReturn(@RequestBody String input) {
        StoringReturnDTO storedData; StoringReturnDetailDTO storedDetailData;
        OrderDTO orderTemp = null; OrderDetailDTO orderDetailTemp = null;
        List<StoringDetailDTO> storingDetailTemp = null;
        String purc_retu_no = null; // 입고 반품 번호 저장용
        InputResultClass result = new InputResultClass(); // 오류 등이 발생했을 경우에는 result에 오류 내용을 담아서 전송
        List<StoringReturnDetailDTO> searchResult;

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

        HashMap<String, String> detailInfo = new HashMap<>();
        detailInfo.put("plord_no", info.get(0));
        detailInfo.put("item_cd", info.get(9));
        storingDetailTemp = storingDetailService.storingDetailOne(detailInfo);
        if(storingDetailTemp.isEmpty()) { // 입고 데이터가 없는데 입고 반품을 시도 => 에러
            result.purc_retu_no = "ERROR-NO STORING DATA";
            return result;
        }

        searchResult = storingReturnDetailService.checkFormerStoringReturnDetailList(orderTemp.getPlord_no()); // 이미 해당 발주 번호로 저장된 물건이 있는지 확인
        if(searchResult.isEmpty()) { // 발주 번호로 저장된 물건이 없으면 새로 입고와 입고 상세 데이터를 생성
            purc_retu_no = storingReturnService.purc_retu_no_Cal(orderTemp.getCorp_cd()); // 입고 번호 생성

            storedData = new StoringReturnDTO(orderTemp, purc_retu_no, info.get(8));
            storedDetailData = new StoringReturnDetailDTO(orderDetailTemp, purc_retu_no, info.get(8),
                    info.get(4), info.get(5), info.get(0), info.get(10), storingDetailTemp.get(0).getPurc_in_no());

            storingReturnService.storingReturnInsert(storedData);
            storingReturnDetailService.storingReturnDetailInsert(storedDetailData);

            result.purc_retu_no = purc_retu_no;
        } else { // 이전에 같은 발주 번호로 물건이 입고되었으면, 입고 상세 데이터만 생성하고, 기존 입고 데이터는 갱신.
            purc_retu_no = searchResult.get(0).getPurc_retu_no();
            storedDetailData = new StoringReturnDetailDTO(orderDetailTemp, purc_retu_no, info.get(8),
                    info.get(4), info.get(5), info.get(0), info.get(10), storingDetailTemp.get(0).getPurc_in_no());
            storingReturnDetailService.storingReturnDetailInsert(storedDetailData);

            storedData = storingReturnService.storingReturnOne(purc_retu_no);
            storingReturnService.storingReturnInsert(storedData);

            result.purc_retu_no = purc_retu_no;
        }

        result.purc_retu_dt = storedData.getPurc_retu_dt();
        result.item_cd = storedDetailData.getItem_cd();
        result.item_nm = itemService.getItemInfo(result.item_cd).getItem_nm();
        result.qty = storedDetailData.getQty();
        return result; // 오류가 없었으면 return
    }

    /* 입고반품번호/품목코드 * n */
    @GetMapping("/storingReturnDelete")
    public boolean deleteStoringReturn(@RequestBody String input) {
        boolean result = false;
        StoringReturnDTO storedData; StoringReturnDetailDTO storedDetailData;
        List<StoringReturnDetailDTO> searchResult;

        // System.out.println(input);

        List<String> info = InfoTokenizer.getInfo(input); // 전송된 Data를 저장

        for(String temp : info) {
            // System.out.println(temp);
        }

        for(int i = 0; i < info.size(); i+=2) { // 들어온 String의 갯수만큼 반복해서 처리
            searchResult = storingReturnDetailService.checkStoringReturnDetailList(info.get(i)); // 이미 해당 입고 번호로 저장된 물건이 있는지 확인
            if(searchResult.size() > 1) { // 입고 반품된 자재가 1개 이상이면 StoringReturnDetail 정보만 제거

                storedDetailData = new StoringReturnDetailDTO(info.get(i), info.get(i + 1));
                storingReturnDetailService.storingReturnDetailDelete(storedDetailData);

                storedData = storingReturnService.storingReturnOne(info.get(i)); // 수정 정보 갱신(ID는 어차피 입력한 사람이 삭제하는 구조이므로 바꾸지 않아도 됨)
                storingReturnService.storingReturnInsert(storedData);
                result = true;
            } else { // 입고된 자재가 1개 뿐이면 StoringReturnDetail, StoringReturn 둘 다 제거

                storedDetailData = new StoringReturnDetailDTO(info.get(i), info.get(i + 1));
                storingReturnDetailService.storingReturnDetailDelete(storedDetailData);

                storedData = storingReturnService.storingReturnOne(info.get(i));
                storingReturnService.storingReturnDelete(storedData);
                result = true;
            }
        }

        return result;
    }
}
