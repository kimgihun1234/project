package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("item")
@Getter
@Setter
@ToString
public class ItemDTO {

    String item_cd; // 품목 코드
    String corp_cd;// 회사 코드
    String item_nm; // 품목 명
    String stan; // 규격

    String ac_divi_cd; // 계정 구분 코드
    String proc_divi_cd; // 조달 구분 코드
    String item_divi_cd; // 품목 구분 코드
    String mcate_cd; // 대분류 코드
    String scate_cd; // 중분류 코드
    String tcate_cd; // 소분류 코드
    String inve_unit; // 재고 단위
    String conv_num; // 환산 계수
    String mana_unit; // 관리 단위
    String mate; // 재질
    String width; // 폭
    String height; // 높이
    String depth; // 깊이
    String density; // 밀도
    String tax_divi; // 세금 구분
    String drawing_no; // 도면 번호

    String use_yn; // 사용 여부
    String remark; // 비고
    String inp_id; // 입력 아이디
    String inp_dttm; // 입력 일시
    String upd_id; // 수정 아이디
    String upd_dttm; // 수정 일시

    public ItemDTO() {}

    public String getItem_nm() {
        return item_nm;
    }

    public String getAc_divi_cd() {
        return ac_divi_cd;
    }
}
