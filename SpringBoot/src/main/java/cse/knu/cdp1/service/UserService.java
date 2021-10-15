package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.UserDTO;

import java.util.List;

public interface UserService {
    public abstract List<UserDTO> getLoginInfo(UserDTO searchData);
}
