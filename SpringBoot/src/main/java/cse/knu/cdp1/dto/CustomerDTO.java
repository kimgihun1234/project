package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("customer")
@Getter
@Setter
@ToString
public class CustomerDTO {

    String cust_cd; // 거래처 코드
    String cust_nm; // 거래처명
    String owner_nm; // 대표자명
    String corp_reg_no; // 법인등록번호
    String corp_no; // 사용자등록번호
    String type_cd; // 타입 코드
    String busi_type; // 업태
    String busi_item; // 종목
    String rece_limit; // 채권 한도
    String zip_no; // 우편 번호
    String addr; // 주소
    String addr_detail; // 주소 상세
    String tel_no; // 전화 번호
    String fax_no; // 팩스 번호
    String email; // 이메일
    String bank_cd; // 은행 코드
    String achr; // 예금주
    String acco_no; // 계좌 번호
    String vaild_st_dt; // 유효 시작 일자
    String vaild_ed_dt; // 유효 종료 일자
    String remark; // 비고
    String use_yn; // 사용 여부
    String inp_id; // 입력 아이디
    String inp_dttm; // 입력 일시
    String upd_id; // 수정 아이디
    String upd_dttm; // 수정 일시

    public CustomerDTO() {}

    public String getCust_nm() {
        return cust_nm;
    }

    public String getCust_cd() {
        return cust_cd;
    }
}
