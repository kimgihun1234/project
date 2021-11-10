package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("unstoringdetail")
@Getter
@Setter
@ToString
public class UnstoringDetailDTO {

    String corp_cd;// 회사 코드
    String ex_no; // 출고 번호
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
    String deli_close_dt; // 납기 마감 일자
    String ex_due_dt; // 출고 예정 일자

    String esti_no; // 견적 번호
    String reord_no; // 수주 번호
    String ex_requ_no; // 출고 요청 번호

    String pj_cd; // 프로젝트 코드
    Double apply_qty;// 적용 수량
    String remark; // 비고
    String inp_id; // 입력 아이디
    String inp_dttm; // 입력 일시
    String upd_id; // 수정 아이디
    String upd_dttm; // 수정 일시

    public UnstoringDetailDTO() {}

    public String getEx_no() {
        return ex_no;
    }

    public String getItem_cd() {
        return item_cd;
    }

    public Double getQty() {
        return qty;
    }

    // Delete용 생성자
    public UnstoringDetailDTO(String ex_no, String item_cd, Double qty) {
        this.ex_no = ex_no;
        this.item_cd = item_cd;
        this.qty = -qty;
    }

    // Insert용 생성자
    public UnstoringDetailDTO(String corp_cd, String ex_no, String item_cd, Double qty, String stor_cd, String loca_cd, String emp_no) {
        this.corp_cd = corp_cd;
        this.ex_no = ex_no;
        // seq는 sql 코드로 생성
        this.item_cd = item_cd;

        this.qty = qty;

        this.stor_cd = stor_cd;
        this.loca_cd = loca_cd;

        this.inp_id = emp_no;

        this.upd_id = emp_no;
    }
}
