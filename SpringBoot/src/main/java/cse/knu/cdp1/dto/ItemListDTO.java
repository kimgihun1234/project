package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("itemlist")
@Getter
@Setter
@ToString
public class ItemListDTO {
    String item_cd;
    String item_nm;
    String stan;
    String comm_cd_nm;
    Double qty;
}
