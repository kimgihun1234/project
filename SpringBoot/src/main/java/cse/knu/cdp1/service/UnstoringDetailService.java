package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.UnstoringDetailDTO;

import java.util.List;

public interface UnstoringDetailService {
    public abstract List<UnstoringDetailDTO> unstoringDetailList();
    public abstract void unstoringDetailInsert(UnstoringDetailDTO insertData);
    public abstract void unstoringDetailDelete(UnstoringDetailDTO deleteData);
}
