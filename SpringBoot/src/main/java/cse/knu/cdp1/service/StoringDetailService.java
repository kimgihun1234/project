package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.StoringDetailDTO;

import java.util.List;

public interface StoringDetailService {
    public abstract List<StoringDetailDTO> storingDetailList();
    public abstract void storingDetailInsert(StoringDetailDTO insertData);
    public abstract void storingDetailDelete(StoringDetailDTO deleteData);
    public abstract List<StoringDetailDTO> checkFormerDetailList(String plord_no);
}
