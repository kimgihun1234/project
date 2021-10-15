package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.UnstoringDTO;

import java.util.List;


public interface UnstoringService {
    public abstract List<UnstoringDTO> unstoringList();
    public abstract void unstoringInsert(UnstoringDTO insertData);
    public abstract void unstoringDelete(UnstoringDTO deleteData);
}
