package cse.knu.cdp1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("listinfo")
@Getter
@Setter
@ToString
public class ListInfo {
    String cust_cd;
    String dt_1;
    String dt_2;
    String item_cd;

    public String getCust_cd() {
        return cust_cd;
    }

    public String getDt_1() {
        return dt_1;
    }

    public String getDt_2() {
        return dt_2;
    }

    public String getItem_cd() {
        return item_cd;
    }
}
