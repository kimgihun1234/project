package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.text.SimpleDateFormat;
import java.util.Date;

@Alias("unstoring")
@Getter
@Setter
@ToString
public class UnstoringDTO {

    String corp_cd; // 회사 코드
    String busi_cd; // 사업장 코드
    String ex_no; // 출고 번호
    String ex_dt; // 출고 일자
    String sale_close_divi; // 매출 마감 구분
    String cust_cd; // 거래처 코드
    String supp_cd; // 납품처
    String pic_cd; // 담당자 코드
    String depa_cd; // 부서 코드
    String emp_no; // 사원 번호

    String remark; // 비고
    String close_yn; // 마감 여부
    String inp_id; // 입력 아이디
    String inp_dttm; // 입력 일시
    String upd_id; // 수정 아이디
    String upd_dttm; // 수정 일시

    public UnstoringDTO() {}

    public String getEx_no() {
        return ex_no;
    }

    public String getEx_dt() {
        return ex_dt;
    }

    public UnstoringDTO(String ex_no) {
        super();
        SimpleDateFormat format1 = new SimpleDateFormat( "yyyyMMdd");
        Date time = new Date();

        this.ex_dt = format1.format(time);
        this.ex_no = ex_no;
    }

    public UnstoringDTO(String corp_cd, String busi_cd, String ex_no, String cust_cd, String emp_no) {
        SimpleDateFormat format1 = new SimpleDateFormat( "yyyyMMdd");
        Date time = new Date();

        this.corp_cd = corp_cd;
        this.busi_cd = busi_cd;
        this.ex_no = ex_no;
        this.ex_dt = format1.format(time);

        this.cust_cd = cust_cd;

        this.inp_id = emp_no;

        this.upd_id = emp_no;
    }
}
