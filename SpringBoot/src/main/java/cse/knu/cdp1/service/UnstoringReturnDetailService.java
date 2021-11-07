package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.UnstoringReturnDetailDTO;
import cse.knu.cdp1.dto.UnstoringReturnListDTO;
import cse.knu.cdp1.dto.UnstoringReturnDetailDTO;
import cse.knu.cdp1.dto.UnstoringReturnListDTO;

import java.util.HashMap;
import java.util.List;

public interface UnstoringReturnDetailService {
    public abstract List<UnstoringReturnDetailDTO> unstoringReturnDetailList();
    public abstract List<UnstoringReturnListDTO> unstoringReturnDetailSpecList(HashMap<String, String> searchInfo);
    public abstract void unstoringReturnDetailInsert(UnstoringReturnDetailDTO insertData);
    public abstract void unstoringReturnDetailDelete(UnstoringReturnDetailDTO deleteData);
    public abstract List<UnstoringReturnDetailDTO> checkUnstoringReturnDetailList(String ex_retu_no);
    public abstract void unstoringReturnDetailUpdate(UnstoringReturnDetailDTO updateData);
}