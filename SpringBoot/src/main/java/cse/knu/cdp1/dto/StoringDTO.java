package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.text.SimpleDateFormat;
import java.util.Date;

@Alias("storing")
@Getter
@Setter
@ToString
public class StoringDTO {

    String corp_cd; // 회사 코드
    String busi_cd; // 사업장 코드
    String purc_in_no; // 구매 입고 번호
    String purc_in_dt; // 구매 입고 일자
    String purc_close_divi; // 구매 마감 구분
    String cust_cd; // 거래처 코드
    String pic_cd; // 담당자 코드
    String depa_cd; // 부서 코드
    String emp_no; // 사원 번호

    String remark; // 비고
    String close_yn; // 마감 여부
    String inp_id; // 입력 아이디
    String inp_dttm; // 입력 일시
    String upd_id; // 수정 아이디
    String upd_dttm; // 수정 일시

    public StoringDTO() {}

    public StoringDTO(String corp_cd, String busi_cd, String purc_in_no) {
        super();
        this.corp_cd = corp_cd;
        this.busi_cd = busi_cd;
        this.purc_in_no = purc_in_no;
    }

    public StoringDTO(OrderDTO input, String emp_no) {
        SimpleDateFormat format1 = new SimpleDateFormat( "yyyyMMdd");
        Date time = new Date();

        this.corp_cd = input.corp_cd;
        this.busi_cd = input.busi_cd;

        this.purc_in_dt = format1.format(time);
        this.purc_close_divi = null;
        this.cust_cd = input.getCust_cd();
        this.pic_cd = null;
        this.depa_cd = null;
        this.emp_no = emp_no;

        this.remark = null;
        this.close_yn = "0";
        this.inp_id = emp_no;

        this.upd_id = emp_no;
    }

    public String getPurc_in_no() { return purc_in_no; }
}
