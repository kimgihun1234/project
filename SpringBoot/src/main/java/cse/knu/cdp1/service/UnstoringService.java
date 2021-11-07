package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.*;
import cse.knu.cdp1.dto.UnstoringDTO;

import java.util.HashMap;
import java.util.List;


public interface UnstoringService {
    public abstract List<UnstoringDTO> unstoringTotalList();
    public abstract List<UnstoringListDTO> unstoringSpecList(HashMap<String, String> searchInfo);
    public abstract UnstoringDTO unstoringOne(String ex_no);
    public abstract List<UnstoringDTO> checkFormerUnstoringList();
    public abstract void unstoringInsert(UnstoringDTO insertData);
    public abstract void unstoringDelete(UnstoringDTO deleteData);
    public abstract String ex_no_Cal(String corp_cd);
}
