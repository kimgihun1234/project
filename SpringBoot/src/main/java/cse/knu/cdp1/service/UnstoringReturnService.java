package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.UnstoringReturnDTO;
import cse.knu.cdp1.dto.UnstoringReturnListDTO;
import cse.knu.cdp1.dto.UnstoringReturnDTO;
import cse.knu.cdp1.dto.UnstoringReturnListDTO;

import java.util.HashMap;
import java.util.List;

public interface UnstoringReturnService {
    public abstract List<UnstoringReturnDTO> unstoringReturnTotalList();
    public abstract List<UnstoringReturnListDTO> unstoringReturnSpecList(HashMap<String, String> searchInfo);
    public abstract UnstoringReturnDTO unstoringReturnOne(String ex_retu_no);
    public abstract List<UnstoringReturnDTO> checkFormerUnstoringReturnList();
    public abstract void unstoringReturnInsert(UnstoringReturnDTO insertData);
    public abstract void unstoringReturnDelete(UnstoringReturnDTO deleteData);
    public abstract String ex_retu_no_Cal(String corp_cd);
}
