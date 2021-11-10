package cse.knu.cdp1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("insertinfo")
@Getter
@Setter
@ToString
public class InsertInfo {
    String cust_cd;
    String stor_cd;
    String loca_cd;
    String item_cd;
    Double qty;

    public InsertInfo() {}

    public String getCust_cd() {
        return cust_cd;
    }

    public String getStor_cd() {
        return stor_cd;
    }

    public String getLoca_cd() {
        return loca_cd;
    }

    public String getItem_cd() {
        return item_cd;
    }

    public Double getQty() {
        return qty;
    }
}
