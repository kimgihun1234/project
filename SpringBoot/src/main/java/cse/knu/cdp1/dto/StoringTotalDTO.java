package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("storingtotal")
@Getter
@Setter
@ToString
public class StoringTotalDTO { // 입고 정보를 한꺼번에 전달받기 위해 사용하는 Class
    StoringDTO storingDTO;
    StoringDetailDTO storingDetailDTO;

    public StoringDTO getStoringDTO() {return storingDTO;}
    public StoringDetailDTO getStoringDetailDTO() {return storingDetailDTO;}
}
