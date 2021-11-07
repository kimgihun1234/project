package cse.knu.cdp1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("storinglist")
@Getter
@Setter
@ToString
public class StoringListDTO {
    String purc_in_dt; // 입고 일자
    String item_cd; // 품목 번호
    String item_nm; // 품목명
    Double qty; // 수량

    String purc_in_no; // 입고 번호
    String cust_cd; // 거래처 번호
    String cust_nm; // 거래처명
    String stor_cd; // 창고 번호
    String stor_nm; // 창고 명
    String loca_cd; // 위치 번호
    String loca_nm; // 위치 명

    public StoringListDTO() {}

    public void setPurc_in_dt(String purc_in_dt) {
        this.purc_in_dt = purc_in_dt;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public void setItem_nm(String item_nm) {
        this.item_nm = item_nm;
    }

    public void setCust_nm(String cust_nm) {
        this.cust_nm = cust_nm;
    }

    public void setStor_nm(String stor_nm) {
        this.stor_nm = stor_nm;
    }

    public void setLoca_nm(String loca_nm) {
        this.loca_nm = loca_nm;
    }

    public String getPurc_in_no() {
        return purc_in_no;
    }

    public String getItem_cd() {
        return item_cd;
    }

    public String getCust_cd() {
        return cust_cd;
    }

    public String getStor_cd() {
        return stor_cd;
    }

    public String getLoca_cd() {
        return loca_cd;
    }
}
