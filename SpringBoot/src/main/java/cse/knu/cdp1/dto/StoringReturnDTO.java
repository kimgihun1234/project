package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("storingreturn")
@Getter
@Setter
@ToString
public class StoringReturnDTO {
    /* 작성 일자 : 2021. 10. 05. */

    String corp_cd; // 회사 코드
    String busi_id; // 사업장 코드
    String purc_retu_no; // 구매 반품 번호
    String purc_retu_dt; // 구매 입고 일자
    String purc_close_divi; // 구매 마감 구분
    String cust_cd; // 거래처 코드

    String pic_no; // 담당자 코드
    String depa_cd; // 부서 코드
    String emp_no; // 사원 번호

    String remark; // 비고
    String close_yn; // 마감 여부
    String inp_id; // 입력 아이디
    String inp_dttm; // 입력 일시
    String upd_id; // 수정 아이디
    String upd_dttm; // 수정 일시

    public StoringReturnDTO() {}
}
