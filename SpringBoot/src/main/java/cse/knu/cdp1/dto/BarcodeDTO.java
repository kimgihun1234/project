package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("barcode")
@Getter
@Setter
@ToString
public class BarcodeDTO {
    /* 작성 일자 : 2021. 10. 05. */

    String corp_cd; // 회사 코드
    String seq; // 순번
    String item_cd; // 품목 코드
    String barcode; // 바코드
    Double qty; // 수량
    Double cnt;
    String remark; // 비고
    String inp_id; // 입력 아이디
    String inp_dttm; // 입력 일시
    String upd_id; // 수정 아이디
    String upd_dttm; // 수정 일시

    public BarcodeDTO() {}
}
