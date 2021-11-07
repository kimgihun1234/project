package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.StoringDetailDTO;
import cse.knu.cdp1.dto.StoringListDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.HashMap;
import java.util.List;

public interface StoringDetailService {
    public abstract List<StoringDetailDTO> storingDetailList();
    public abstract List<StoringListDTO> storingDetailSpecList(HashMap<String, String> searchInfo);
    public abstract void storingDetailInsert(StoringDetailDTO insertData);
    public abstract void storingDetailDelete(StoringDetailDTO deleteData);
    public abstract List<StoringDetailDTO> checkStoringDetailList(String purc_in_no);
    public abstract void storingDetailUpdate(StoringDetailDTO updateData);
}
