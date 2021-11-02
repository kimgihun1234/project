package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.StoringReturnDetailDTO;
import cse.knu.cdp1.dto.StoringReturnListDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.HashMap;
import java.util.List;

public interface StoringReturnDetailService {
    public abstract List<StoringReturnDetailDTO> storingReturnDetailList();
    public abstract List<StoringReturnListDTO> storingReturnDetailSpecList(HashMap<String, String> searchInfo);
    public abstract void storingReturnDetailInsert(StoringReturnDetailDTO insertData);
    public abstract void storingReturnDetailDelete(StoringReturnDetailDTO deleteData);
    public abstract List<StoringReturnDetailDTO> checkFormerStoringReturnDetailList(String plord_no);
    public abstract List<StoringReturnDetailDTO> checkStoringReturnDetailList(String purc_retu_no);
}
