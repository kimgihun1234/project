package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.*;
import cse.knu.cdp1.dto.UnstoringDetailDTO;

import java.util.HashMap;
import java.util.List;

public interface UnstoringDetailService {
    public abstract List<UnstoringDetailDTO> unstoringDetailList();
    public abstract List<UnstoringListDTO> unstoringDetailSpecList(HashMap<String, String> searchInfo);
    public abstract void unstoringDetailInsert(UnstoringDetailDTO insertData);
    public abstract void unstoringDetailDelete(UnstoringDetailDTO deleteData);
    public abstract List<UnstoringDetailDTO> checkUnstoringDetailList(String ex_no);
    public abstract void unstoringDetailUpdate(UnstoringDetailDTO updateData);}
