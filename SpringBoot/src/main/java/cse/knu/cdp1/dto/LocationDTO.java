package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("location")
@Getter
@Setter
@ToString
public class LocationDTO {

    String loca_cd; // 위치 코드
    String stor_cd; // 창고 코드
    String loca_nm; // 위치 명
    String remark; // 비고
    String use_yn; // 사용 여부
    String inp_id; // 입력 아이디
    String inp_dttm; // 입력 일시
    String upd_id; // 수정 아이디
    String upd_dttm; // 수정 일시

    public LocationDTO() {}

    public String getStor_cd() {
        return stor_cd;
    }

    public String getLoca_nm() {
        return loca_nm;
    }

    public String getLoca_cd() {
        return loca_cd;
    }
}
