package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("storingreturndetail")
@Getter
@Setter
@ToString
public class StoringReturnDetailDTO {

    String corp_cd;// 회사 코드
    String purc_retu_no; // 구매 반품 번호
    String seq; // 순번
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

    String purc_requ_no; // 구매 요청 번호
    String plord_no; // 발주 번호
    String purc_in_no; // 구매 입고 번호
    String pj_cd; // 프로젝트 코드

    String remark; // 비고
    Double apply_qty;// 적용 수량

    String inp_id; // 입력 아이디
    String inp_dttm; // 입력 일시
    String upd_id; // 수정 아이디
    String upd_dttm; // 수정 일시

    public String getPurc_retu_no() {
        return purc_retu_no;
    }

    public String getItem_cd() {
        return item_cd;
    }

    public Double getQty() {
        return qty;
    }

    public StoringReturnDetailDTO() {}

    // Delete용 생성자
    public StoringReturnDetailDTO(String purc_retu_no, String item_cd, Double qty) {
        this.purc_retu_no = purc_retu_no;
        this.item_cd = item_cd;
        this.qty = -qty;
    }

    // Insert용 생성자
    public StoringReturnDetailDTO(String corp_cd, String purc_retu_no, String item_cd, Double qty, String stor_cd, String loca_cd, String emp_no) {
        this.corp_cd = corp_cd;
        this.purc_retu_no = purc_retu_no;
        // seq는 sql 코드로 생성
        this.item_cd = item_cd;

        this.qty = qty;

        this.stor_cd = stor_cd;
        this.loca_cd = loca_cd;

        this.inp_id = emp_no;

        this.upd_id = emp_no;
    }
}
