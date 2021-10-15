package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("storingdetail")
@Getter
@Setter
@ToString
public class StoringDetailDTO {
    /* 작성 일자 : 2021. 10. 04. */

    String seq; // 순번
    String corp_cd;// 회사 코드
    String purc_in_no; // 구매 입고 번호
    String item_cd; // 품목 코드
    String tax_divi; // 세금 구분
    String vat_divi; // 부가세 구분
    Double qty; // 수량
    Double upr; // 단가
    Double sup_amt; // 공급가 금액
    Double tax; // 세금
    Double tot_amt; // 합계 금액
    String stor_cd; // 창고 코드
    String loca_cd; // 위치 코드

    String deli_close_dt; // 납기 마감 일자
    String in_due_dt; // 입고 예정 일자
    String purc_requ_no; // 구매 요청 번호
    String plord_no; // 발주 번호
    String pj_cd; // 프로젝트 코드
    Double apply_qty;// 적용 수량

    String remark; // 비고
    String inp_id; // 입력 아이디
    String inp_dttm; // 입력 일시
    String upd_id; // 수정 아이디
    String upd_dttm; // 수정 일시

    public StoringDetailDTO() {}

    public String getPurc_in_no() { return purc_in_no; }
}
