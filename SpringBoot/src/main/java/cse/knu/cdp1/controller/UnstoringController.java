package cse.knu.cdp1.controller;

import cse.knu.cdp1.DeleteInfo;
import cse.knu.cdp1.InfoTokenizer;
import cse.knu.cdp1.InsertInfo;
import cse.knu.cdp1.ListInfo;
import cse.knu.cdp1.dto.*;
import cse.knu.cdp1.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Autowired
    SecurityService securityService;

    @PostMapping("/unstoringList")
    public List<UnstoringListDTO> unstoringList(@RequestBody ListInfo input) {
        HashMap<String, String> searchInfo = new HashMap<>();

        searchInfo.put("cust_cd", input.getCust_cd());
        searchInfo.put("ex_dt_1", input.getDt_1());
        searchInfo.put("ex_dt_2", input.getDt_2());

        // 조건에 맞는 UnstoringList 생성
        List<UnstoringListDTO> searchResult = unstoringService.unstoringSpecList(searchInfo);

        for(UnstoringListDTO listTemp : searchResult) {
            searchInfo.put("item_cd", listTemp.getItem_cd());
            listTemp.setItem_nm(itemService.getItemInfo(listTemp.getItem_cd()).getItem_nm());
        }

        return searchResult;
    }

    @PostMapping("/unstoringDetailList")
    public List<UnstoringListDTO> unstoringDetailList(@RequestBody ListInfo input) {
        HashMap<String, String> searchInfo = new HashMap<>();

        searchInfo.put("cust_cd", input.getCust_cd());
        searchInfo.put("ex_dt_1", input.getDt_1());
        searchInfo.put("ex_dt_2", input.getDt_2());
        searchInfo.put("item_cd", input.getItem_cd());

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

    @Alias("unstoringinsertresult")
    @Getter
    @Setter
    @ToString
    public class InsertResult {
        String ex_no;
        // String qty;

        public InsertResult() {}
    }

    @PostMapping("/unstoringInsert")
    public InsertResult insertUnstoring(@RequestParam(name = "jwt") String jwt, @RequestBody InsertInfo input) {
        UnstoringDTO storedData; UnstoringDetailDTO storedDetailData;
        String ex_no = null; // 출고 번호 저장용
        InsertResult result = new InsertResult(); // 오류 등이 발생했을 경우에는 result에 오류 내용을 담아서 전송
        List<UnstoringDTO> searchResult; List<UnstoringDetailDTO> searchDetailResult;

        // System.out.println(input);

        List<String> jwtInfo = InfoTokenizer.getInfo(securityService.getSubject(jwt));

        for(String temp : jwtInfo) {
            // System.out.println(temp);
        }

        searchResult = unstoringService.checkFormerUnstoringList(); // 오늘 날짜 기준으로 이미 출고된 적이 있는지 확인
        if(searchResult.isEmpty()) { // 없으면 새로 출고와 출고 상세 데이터를 생성
            ex_no = unstoringService.ex_no_Cal(jwtInfo.get(1)); // 출고 번호 생성

            storedData = new UnstoringDTO(jwtInfo.get(1), jwtInfo.get(2), ex_no, input.getCust_cd(), jwtInfo.get(0));
            storedDetailData = new UnstoringDetailDTO(jwtInfo.get(1), ex_no, input.getItem_cd(), input.getQty(), input.getStor_cd(), input.getLoca_cd(), jwtInfo.get(0));

            unstoringService.unstoringInsert(storedData);
            unstoringDetailService.unstoringDetailInsert(storedDetailData);

            result.ex_no = ex_no;
        } else { // 이전에 같은 출고 번호로 물건이 출고되었으면, 출고 상세 데이터만 생성하고, 기존 출고 데이터는 갱신.
            ex_no = searchResult.get(0).getEx_no();

            searchDetailResult = unstoringDetailService.checkUnstoringDetailList(ex_no);

            storedDetailData = new UnstoringDetailDTO(jwtInfo.get(1), ex_no, input.getItem_cd(), input.getQty(), input.getStor_cd(), input.getLoca_cd(), jwtInfo.get(0));
            boolean checkDuplicate = false;
            for(UnstoringDetailDTO listTemp : searchDetailResult) {
                if(input.getItem_cd().equals(listTemp.getItem_cd())) { // 만약 같은 물품 번호로 들어간게 있으면
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


    @Alias("unstoringdeleteresult")
    @Getter
    @Setter
    @ToString
    public class DeleteResult {
        int result;
        // String qty;

        public DeleteResult() {}
    }
    /* 출고번호/품목코드/수량 */
    @PostMapping("/unstoringDelete")
    public ResponseEntity<DeleteResult> deleteStoring(@RequestBody List<DeleteInfo> input) {
        DeleteResult result = new DeleteResult();
        UnstoringDTO storedData; UnstoringDetailDTO storedDetailData; UnstoringDetailDTO checkingData = null;
        List<UnstoringDetailDTO> searchResult;

        int check = 0;
        for(DeleteInfo info : input) { // 들어온 String의 갯수만큼 반복해서 처리
            searchResult = unstoringDetailService.checkUnstoringDetailList(info.getNo()); // 해당 출고 번호에 대한 상세 리스트를 먼저 불러옴
            if(searchResult.isEmpty()) { // 검색 결과가 아예 없으면
                result.result = check + 1; // 순서 중에 몇 번째까지만 진행되었음을 return
                return new ResponseEntity(result, HttpStatus.SERVICE_UNAVAILABLE); // 503으로 반환
            }

            storedDetailData = new UnstoringDetailDTO(info.getNo(), info.getItem_cd(), info.getQty()); // delete를 위한 class 생성

            for(UnstoringDetailDTO listTemp : searchResult) { // 같은 물품 번호가 있는 상세 정보에 대해 먼저 찾음
                checkingData = new UnstoringDetailDTO(listTemp.getEx_no(), listTemp.getItem_cd(), -listTemp.getQty());
                if(info.getItem_cd().equals(listTemp.getItem_cd())) { // 만약 같은 물품 번호가 있으면
                    unstoringDetailService.unstoringDetailUpdate(storedDetailData);// 갯수 업데이트를 먼저 실시
                    break;
                }
            }

            unstoringDetailService.unstoringDetailDelete(storedDetailData); // 갯수 업데이트 후 Delete 실시. qty가 0이면 삭제.

            searchResult = unstoringDetailService.checkUnstoringDetailList(info.getNo()); // Delete 이후 List를 확인

            for(UnstoringDetailDTO listTemp : searchResult) { // delete 결과 확인
                if(checkingData.getItem_cd().equals(listTemp.getItem_cd()) && (checkingData.getQty() - info.getQty()) != (listTemp.getQty())) { // 예상한대로 Delete되지 않았으면(갯수가 맞지 않음, 삭제하려는 데이터가 없었음)
                    result.result = check + 1; // 순서 중에 몇 번째까지만 진행되었음을 return
                    return new ResponseEntity(result, HttpStatus.SERVICE_UNAVAILABLE); // 503으로 반환
                }
            }

            if(searchResult.isEmpty()) { // 더이상 해당 출고 번호에 대한 출고 상세 정보가 없으면
                storedData = unstoringService.unstoringOne(info.getNo()); // 출고 정보 삭제를 위해 해당 출고 정보를 갖고 오고
                unstoringService.unstoringDelete(storedData); // 그 출고 정보를 삭제
            }

            check++;
        }

        result.result = 0;


        return new ResponseEntity(result, HttpStatus.OK);
    }
}
