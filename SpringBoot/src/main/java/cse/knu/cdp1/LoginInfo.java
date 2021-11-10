package cse.knu.cdp1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("logininfo")
@Getter
@Setter
@ToString
public class LoginInfo {
    String id;
    String pw;

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }
}
