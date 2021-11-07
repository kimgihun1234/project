package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.StoringDTO;
import cse.knu.cdp1.dto.StoringListDTO;
import cse.knu.cdp1.dto.StoringReturnDTO;
import cse.knu.cdp1.dto.StoringReturnListDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.HashMap;
import java.util.List;

public interface StoringReturnService {
    public abstract List<StoringReturnDTO> storingReturnTotalList();
    public abstract List<StoringReturnListDTO> storingReturnSpecList(HashMap<String, String> searchInfo);
    public abstract StoringReturnDTO storingReturnOne(String purc_retu_no);
    public abstract List<StoringReturnDTO> checkFormerStoringReturnList();
    public abstract void storingReturnInsert(StoringReturnDTO insertData);
    public abstract void storingReturnDelete(StoringReturnDTO deleteData);
    public abstract String purc_retu_no_Cal(String corp_cd);
}
