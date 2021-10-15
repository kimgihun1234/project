package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("unstoringtotal")
@Getter
@Setter
@ToString
public class UnstoringTotalDTO { // 출고 정보를 한꺼번에 전달받기 위해 사용하는 Class
    UnstoringDTO unstoringDTO;
    UnstoringDetailDTO unstoringDetailDTO;

    public UnstoringDTO getStoringDTO() {return unstoringDTO;}
    public UnstoringDetailDTO getStoringDetailDTO() {return unstoringDetailDTO;}
}
