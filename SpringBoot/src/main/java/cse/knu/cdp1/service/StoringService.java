package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.StoringDTO;
import cse.knu.cdp1.dto.StoringListDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.HashMap;
import java.util.List;


public interface StoringService {
    public abstract List<StoringDTO> storingTotalList();
    public abstract List<StoringListDTO> storingSpecList(HashMap<String, String> searchInfo);
    public abstract StoringDTO storingOne(String purc_in_no);
    public abstract String calDate (HashMap<String, String> searchInfo);
    public abstract double calTotalSum (HashMap<String, String> searchInfo);
    public abstract void storingInsert(StoringDTO insertData);
    public abstract void storingDelete(StoringDTO deleteData);
    public abstract String purc_in_no_Cal(String corp_cd);
}
