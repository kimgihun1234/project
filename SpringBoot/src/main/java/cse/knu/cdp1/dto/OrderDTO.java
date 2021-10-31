package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("order")
@Getter
@Setter
@ToString
public class OrderDTO {

    String corp_cd; // 회사 코드
    String busi_cd; // 사업장 코드
    String plord_no; // 발주 번호
    String plord_dt; // 발주 일자
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

    public OrderDTO() {}

    public String getCorp_cd() {
        return corp_cd;
    }

    public String getPlord_no() {
        return plord_no;
    }

    public String getCust_cd() { return cust_cd; }
}
