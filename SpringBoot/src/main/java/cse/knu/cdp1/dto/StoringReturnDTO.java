package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.text.SimpleDateFormat;
import java.util.Date;

@Alias("storingreturn")
@Getter
@Setter
@ToString
public class StoringReturnDTO {

    String corp_cd; // 회사 코드
    String busi_cd; // 사업장 코드
    String purc_retu_no; // 구매 반품 번호
    String purc_retu_dt; // 구매 입고 일자
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

    public StoringReturnDTO() {}

    public StoringReturnDTO(String purc_retu_no) {
        super();
        SimpleDateFormat format1 = new SimpleDateFormat( "yyyyMMdd");
        Date time = new Date();

        this.purc_retu_dt = format1.format(time);
        this.purc_retu_no = purc_retu_no;
    }

    public StoringReturnDTO(String corp_cd, String busi_cd, String purc_retu_no, String cust_cd, String emp_no) {
        SimpleDateFormat format1 = new SimpleDateFormat( "yyyyMMdd");
        Date time = new Date();

        this.corp_cd = corp_cd;
        this.busi_cd = busi_cd;
        this.purc_retu_no = purc_retu_no;
        this.purc_retu_dt = format1.format(time);

        this.cust_cd = cust_cd;

        this.inp_id = emp_no;

        this.upd_id = emp_no;
    }

    public String getPurc_retu_no() { return purc_retu_no; }

    public String getPurc_retu_dt() {
        return purc_retu_dt;
    }
}
