package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.StoringDTO;
import cse.knu.cdp1.dto.StoringListDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.HashMap;
import java.util.List;

public interface StoringDAO {
    public abstract List<StoringDTO> storingTotalList(SqlSessionTemplate session);
    public abstract List<StoringListDTO> storingSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo);
    public abstract StoringDTO storingOne(SqlSessionTemplate session, String purc_in_no);
    public abstract List<StoringDTO> checkFormerStoringList(SqlSessionTemplate session, String purc_in_dt);
    public abstract void storingInsert(SqlSessionTemplate session, StoringDTO insertData);
    public abstract void storingDelete(SqlSessionTemplate session, StoringDTO deleteData);
    public abstract String purc_in_no_Cal(SqlSessionTemplate session, String corp_cd);
}
