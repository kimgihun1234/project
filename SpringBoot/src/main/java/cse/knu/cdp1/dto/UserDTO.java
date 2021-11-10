package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("user")
@Getter
@Setter
@ToString
public class UserDTO {

    String emp_no; // 사원 번호
    String emp_nm; // 사원 명
    String emp_state; // 사원 상태
    String resi_no; // 주민등록 번호
    String biryr_md; // 생년 월일
    String sex_cd; // 성별 코드
    String eco_dt; // 입사 일자
    String rco_dt; // 퇴사 일자
    String emp_id; // 사원 아이디
    String zip_no; // 우편 번호
    String addr; // 주소
    String addr_detail; // 주소 상세
    String nati_cd; // 국적 코드
    String fore_divi_cd; // 외국인 구분 코드
    String email; // 이메일
    String corp_cd; // 회사 번호
    String busi_cd; // 사업장 코드
    String resp_divi; // 직책 구분
    String posi_divi; // 직위 구분
    String acc_divi; // 접속구분

    String remark; // 비고
    String pwd; // 비밀 번호
    String pwd_upd_dt; // 비밀번호 수정 일자
    String inp_id; // 입력 아이디
    String inp_dttm; // 입력 일시
    String upd_id; // 수정 아이디
    String upd_dttm; // 수정 일시

    public UserDTO() {}

    public UserDTO(String emp_id, String pwd) {
        this.emp_id = emp_id;
        this.pwd = pwd;
    }

    public String getEmp_no() {
        return emp_no;
    }

    public String getCorp_cd() {
        return corp_cd;
    }

    public String getBusi_cd() {
        return busi_cd;
    }
}
