package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.UnstoringReturnDTO;
import cse.knu.cdp1.dto.UnstoringReturnListDTO;
import cse.knu.cdp1.dto.UnstoringReturnDTO;
import cse.knu.cdp1.dto.UnstoringReturnListDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.HashMap;
import java.util.List;

public interface UnstoringReturnDAO {
    public abstract List<UnstoringReturnDTO> unstoringReturnTotalList(SqlSessionTemplate session);
    public abstract List<UnstoringReturnListDTO> unstoringReturnSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo);
    public abstract UnstoringReturnDTO unstoringReturnOne(SqlSessionTemplate session, String ex_retu_no);
    public abstract List<UnstoringReturnDTO> checkFormerUnstoringReturnList(SqlSessionTemplate session, String ex_retu_dt);
    public abstract void unstoringReturnInsert(SqlSessionTemplate session, UnstoringReturnDTO insertData);
    public abstract void unstoringReturnDelete(SqlSessionTemplate session, UnstoringReturnDTO deleteData);
    public abstract String ex_retu_no_Cal(SqlSessionTemplate session, String corp_cd);
}
