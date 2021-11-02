package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.StoringDTO;
import cse.knu.cdp1.dto.StoringListDTO;
import cse.knu.cdp1.dto.StoringReturnDTO;
import cse.knu.cdp1.dto.StoringReturnListDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.HashMap;
import java.util.List;

public interface StoringReturnDAO {
    public abstract List<StoringReturnDTO> storingReturnTotalList(SqlSessionTemplate session);
    public abstract List<StoringReturnListDTO> storingReturnSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo);
    public abstract StoringReturnDTO storingReturnOne(SqlSessionTemplate session, String purc_retu_no);
    public abstract String calStoringReturnDate (SqlSessionTemplate session, HashMap<String, String> searchInfo);
    public abstract double calStoringReturnTotalSum (SqlSessionTemplate session, HashMap<String, String> searchInfo);
    public abstract void storingReturnInsert(SqlSessionTemplate session, StoringReturnDTO insertData);
    public abstract void storingReturnDelete(SqlSessionTemplate session, StoringReturnDTO deleteData);
    public abstract String purc_retu_no_Cal(SqlSessionTemplate session, String corp_cd);
}
