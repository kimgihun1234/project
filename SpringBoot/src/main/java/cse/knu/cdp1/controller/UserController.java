package cse.knu.cdp1.controller;

import cse.knu.cdp1.LoginInfo;
import cse.knu.cdp1.dto.UserDTO;
import cse.knu.cdp1.service.SecurityService;
import cse.knu.cdp1.service.UserService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @Alias("loginresult")
    @Getter
    @Setter
    @ToString
    public class ResultClass {
        String data;
    }

    @PostMapping("/login")
    public ResultClass checkLoginInfo(@RequestBody LoginInfo idpw) {
        ResultClass result = new ResultClass();

        UserDTO loginInfo = new UserDTO(idpw.getId(), idpw.getPw());

        List<UserDTO> searchResult = userService.getLoginInfo(loginInfo);

        if(searchResult.isEmpty() || searchResult.size() > 1) {
            result.data = "fail";
            return result;
        }

        UserDTO searchTemp = searchResult.get(0);

        result.data = securityService.createTime(searchTemp.getEmp_no() + "/"
                + searchTemp.getCorp_cd() + "/"
                + searchTemp.getBusi_cd(), 60 * 600 * 1000); // 시간 * 분 * 밀리초

        /* 사원번호 + 회사번호 + 사업장번호에 대한 JWT String */
        return result;
    }
}
