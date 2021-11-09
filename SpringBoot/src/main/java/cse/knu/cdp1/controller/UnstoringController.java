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
public class UnstoringController {
    @Autowired
    UnstoringService unstoringService;

    @Autowired
    UnstoringDetailService unstoringDetailService;

    @Autowired
    ItemService itemService;

    @Autowired
    CustomerService customerService;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    LocationService locationService;

    @GetMapping("/unstoringList")
    public List<UnstoringListDTO> unstoringList(@RequestBody String input) {
        HashMap<String, String> searchInfo = new HashMap<>();

        List<String> info = InfoTokenizer.getInfo(input); // 전송된 Data를 저장
        searchInfo.put("cust_cd", info.get(0));
        searchInfo.put("ex_dt_1", info.get(1));
        searchInfo.put("ex_dt_2", info.get(2));

        // 조건에 맞는 UnstoringList 생성
        List<UnstoringListDTO> searchResult = unstoringService.unstoringSpecList(searchInfo);

        for(UnstoringListDTO listTemp : searchResult) {
            searchInfo.put("item_cd", listTemp.getItem_cd());
            listTemp.setItem_nm(itemService.getItemInfo(listTemp.getItem_cd()).getItem_nm());
        }

        return searchResult;
    }

    @GetMapping("/unstoringDetailList")
    public List<UnstoringListDTO> unstoringDetailList(@RequestBody String input) {
        HashMap<String, String> searchInfo = new HashMap<>();

        List<String> info = InfoTokenizer.getInfo(input); // 전송된 Data를 저장
        searchInfo.put("cust_cd", info.get(0));
        searchInfo.put("ex_dt_1", info.get(1));
        searchInfo.put("ex_dt_2", info.get(2));
        searchInfo.put("item_cd", info.get(3));

        // 조건에 맞는 UnstoringDetailList 생성
        List<UnstoringListDTO> searchResult = unstoringDetailService.unstoringDetailSpecList(searchInfo);

        for(UnstoringListDTO listTemp : searchResult) {
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

    /* cust_cd, stor_cd, loca_cd, item_cd, qty, corp_cd, busi_cd, emp_no */
    /*    0        1        2        3      4      5         6       7   */
    @Alias("unstoringinsertinfo")
    @Getter
    @Setter
    @ToString
    public class InsertInfo {
        String cust_cd;
        String stor_cd;
        String loca_cd;
        String item_cd;
        Double qty;
        String corp_cd;
        String busi_cd;
        String emp_no;

        public InsertInfo() {}
    }
    
    @Alias("unstoringinsertresult")
    @Getter
    @Setter
    @ToString
    public class InsertResult {
        String ex_no;
        // Double qty;

        public InsertResult() {}
    }

    @GetMapping("/unstoringInsert")
    public InsertResult insertUnstoring(@RequestBody String input) {
        UnstoringDTO storedData; UnstoringDetailDTO storedDetailData;
        String ex_no = null; // 출고 번호 저장용
        InsertResult result = new InsertResult(); // 오류 등이 발생했을 경우에는 result에 오류 내용을 담아서 전송
        List<UnstoringDTO> searchResult; List<UnstoringDetailDTO> searchDetailResult;

        // System.out.println(input);

        List<String> info = InfoTokenizer.getInfo(input); // 전송된 Data를 저장

        for(String temp : info) {
            // System.out.println(temp);
        }

        searchResult = unstoringService.checkFormerUnstoringList(); // 오늘 날짜 기준으로 이미 출고된 적이 있는지 확인
        if(searchResult.isEmpty()) { // 없으면 새로 출고와 출고 상세 데이터를 생성
            ex_no = unstoringService.ex_no_Cal(info.get(5)); // 출고 번호 생성

            storedData = new UnstoringDTO(info.get(5), info.get(6), ex_no, info.get(0), info.get(7));
            storedDetailData = new UnstoringDetailDTO(info.get(5), ex_no, info.get(3), info.get(4), info.get(1), info.get(2), info.get(7));

            unstoringService.unstoringInsert(storedData);
            unstoringDetailService.unstoringDetailInsert(storedDetailData);

            result.ex_no = ex_no;
        } else { // 이전에 같은 출고 번호로 물건이 출고되었으면, 출고 상세 데이터만 생성하고, 기존 출고 데이터는 갱신.
            ex_no = searchResult.get(0).getEx_no();

            searchDetailResult = unstoringDetailService.checkUnstoringDetailList(ex_no);

            storedDetailData = new UnstoringDetailDTO(info.get(5), ex_no, info.get(3), info.get(4), info.get(1), info.get(2), info.get(7));
            boolean checkDuplicate = false;
            for(UnstoringDetailDTO listTemp : searchDetailResult) {
                if(info.get(3).equals(listTemp.getItem_cd())) { // 만약 같은 물품 번호로 들어간게 있으면
                    unstoringDetailService.unstoringDetailUpdate(storedDetailData);// 갯수 업데이트만 실시
                    checkDuplicate = true;
                    break;
                }
            }

            if(checkDuplicate == false) unstoringDetailService.unstoringDetailInsert(storedDetailData); // 중복되는 물품 번호 없으면 새로 생성

            storedData = unstoringService.unstoringOne(ex_no); // 데이터 갱신만 실시
            unstoringService.unstoringInsert(storedData);

            result.ex_no = ex_no;
        }

        // result.qty = info.get(4); // 갯수 반환 => delete에서 사용
        return result; // 오류가 없었으면 return
    }

    @Alias("unstoringdeleteinfo")
    @Getter
    @Setter
    @ToString
    public class DeleteInfo {
        String ex_no;
        String item_cd;
        Double qty;

        public DeleteInfo() {}
    }
    
    /* 출고번호/품목코드/수량 */
    @GetMapping("/unstoringDelete")
    public boolean deleteUnstoring(@RequestBody String input) {
        boolean result = false;
        UnstoringDTO storedData; UnstoringDetailDTO storedDetailData;
        List<UnstoringDetailDTO> searchResult;

        // System.out.println(input);

        List<String> info = InfoTokenizer.getInfo(input); // 전송된 Data를 저장

        for(String temp : info) {
            // System.out.println(temp);
        }

        for(int i = 0; i < info.size(); i+=3) { // 들어온 String의 갯수만큼 반복해서 처리
            searchResult = unstoringDetailService.checkUnstoringDetailList(info.get(i)); // 해당 출고 번호에 대한 상세 리스트를 먼저 불러옴

            storedDetailData = new UnstoringDetailDTO(info.get(i), info.get(i + 1), info.get(i + 2)); // delete를 위한 class 생성
            unstoringDetailService.unstoringDetailDelete(storedDetailData);

            for(UnstoringDetailDTO listTemp : searchResult) { // 같은 물품 번호가 있는 상세 정보에 대해 먼저 찾음
                if(info.get(i + 1).equals(listTemp.getItem_cd())) { // 만약 같은 물품 번호가 있으면
                    unstoringDetailService.unstoringDetailUpdate(storedDetailData);// 갯수 업데이트를 먼저 실시
                    break;
                }
            }

            unstoringDetailService.unstoringDetailDelete(storedDetailData); // 갯수 업데이트 후 Delete 실시. qty가 0이면 삭제.

            searchResult = unstoringDetailService.checkUnstoringDetailList(info.get(i)); // Delete 이후 List를 확인

            if(searchResult.isEmpty()) { // 더이상 해당 출고 번호에 대한 출고 상세 정보가 없으면
                storedData = unstoringService.unstoringOne(info.get(i)); // 출고 정보 삭제를 위해 해당 출고 정보를 갖고 오고
                unstoringService.unstoringDelete(storedData); // 그 출고 정보를 삭제
            }
        }

        result = true;

        return result;
    }
}
