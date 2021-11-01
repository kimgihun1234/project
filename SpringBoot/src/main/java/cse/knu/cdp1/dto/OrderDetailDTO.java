package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("orderdetail")
@Getter
@Setter
@ToString
public class OrderDetailDTO {

    String seq; // 순번
    String corp_cd;// 회사 코드
    String plord_no; // 발주 번호
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
    String pj_cd; // 프로젝트 코드

    Double apply_qty;// 적용 수량
    String purc_requ_no; // 구매 요청 번호
    String remark; // 비고
    String inp_id; // 입력 아이디
    String inp_dttm; // 입력 일시
    String upd_id; // 수정 아이디
    String upd_dttm; // 수정 일시

    public OrderDetailDTO() {}

    public String getPlord_no() {
        return plord_no;
    }

    public String getItem_cd() {
        return item_cd;
    }

    public String getSeq() {
        return seq;
    }

    public String getCorp_cd() {
        return corp_cd;
    }

    public String getTax_divi() {
        return tax_divi;
    }

    public String getVat_divi() {
        return vat_divi;
    }

    public Double getUpr() {
        return upr;
    }

    public Double getSup_amt() {
        return sup_amt;
    }

    public Double getTax() {
        return tax;
    }

    public Double getTot_amt() {
        return tot_amt;
    }

    public String getDeli_close_dt() {
        return deli_close_dt;
    }

    public String getIn_due_dt() {
        return in_due_dt;
    }

    public String getPurc_requ_no() {
        return purc_requ_no;
    }

    public String getPj_cd() {
        return pj_cd;
    }

    public Double getApply_qty() {
        return apply_qty;
    }

    public String getInfo() {
        return plord_no + "/" + seq + "/" + item_cd;
    }
}
