package cse.knu.cdp1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("barcodeinfo")
@Getter
@Setter
@ToString
public class BarcodeInfo {
    String barcode;

    public BarcodeInfo() {}

    public String getBarcode() {
        return barcode;
    }
}
