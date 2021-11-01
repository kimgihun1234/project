package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("storinginfo")
@Getter
@Setter
@ToString
public class StoringInfoDTO {
    String plord_no; // 발주 번호
    String cust_cd; // 거래처 코드
    String cust_nm; // 거래처명
    String stor_cd; // 창고 코드
    String stor_nm; // 창고 명
    String loca_cd; // 위치 코드
    String loca_nm; // 위치 명
    String emp_no; // 사원 번호
    String emp_id; // 사원 아이디
    String item_cd; // 품목 코드
    Double qty; // 수량

}
