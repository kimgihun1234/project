package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.text.SimpleDateFormat;
import java.util.Date;

@Alias("storingdetail")
@Getter
@Setter
@ToString
public class StoringDetailDTO {

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

    public StoringDetailDTO(OrderDetailDTO input, String emp_no, String stor_cd, String loca_cd, String plord_no, String qty) {
        this.seq = input.seq;
        this.corp_cd = input.corp_cd;

        this.item_cd = input.getItem_cd();
        this.tax_divi = input.tax_divi;
        this.vat_divi = input.vat_divi;
        this.qty = Double.parseDouble(qty);
        this.upr = input.upr;
        this.sup_amt = input.sup_amt;
        this.tax = input.tax;
        this.tot_amt = input.tot_amt;
        this.stor_cd = stor_cd;
        this.loca_cd = loca_cd;

        this.deli_close_dt = input.deli_close_dt;
        this.in_due_dt = input.in_due_dt;
        this.purc_requ_no = input.purc_requ_no;
        this.plord_no = plord_no;
        this.pj_cd = input.pj_cd;
        this.apply_qty = input.apply_qty;

        this.remark = null;

        this.inp_id = emp_no;

        this.upd_id = emp_no;
    }
}
