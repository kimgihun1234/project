package cse.knu.cdp1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("deleteinfo")
@Getter
@Setter
@ToString
public class DeleteInfo {
    String no;
    String item_cd;
    Double qty;

    public DeleteInfo() {}

    public String getNo() {
        return no;
    }

    public String getItem_cd() {
        return item_cd;
    }

    public Double getQty() {
        return qty;
    }
}
