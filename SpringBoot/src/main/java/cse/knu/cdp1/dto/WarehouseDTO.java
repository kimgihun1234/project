package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("warehouse")
@Getter
@Setter
@ToString
public class WarehouseDTO {

    String corp_cd; // 회사 코드
    String busi_cd; // 사업장 코드
    String stor_cd; // 창고 코드
    String stor_nm; // 창고 명
    String stor_divi; // 창고 구분
    String remark; // 비고
    String use_yn; // 사용 여부
    String inp_id; // 입력 아이디
    String inp_dttm; // 입력 일시
    String upd_id; // 수정 아이디
    String upd_dttm; // 수정 일시

    public WarehouseDTO() {}

    public String getStor_cd() {
        return stor_cd;
    }

    public String getStor_nm() {
        return stor_nm;
    }
}
