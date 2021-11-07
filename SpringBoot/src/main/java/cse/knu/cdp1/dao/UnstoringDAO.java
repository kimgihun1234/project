package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.UnstoringDTO;
import cse.knu.cdp1.dto.UnstoringListDTO;
import cse.knu.cdp1.dto.UnstoringDTO;
import cse.knu.cdp1.dto.UnstoringListDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.HashMap;
import java.util.List;

public interface UnstoringDAO {
    public abstract List<UnstoringDTO> unstoringTotalList(SqlSessionTemplate session);
    public abstract List<UnstoringListDTO> unstoringSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo);
    public abstract UnstoringDTO unstoringOne(SqlSessionTemplate session, String ex_no);
    public abstract List<UnstoringDTO> checkFormerUnstoringList(SqlSessionTemplate session, String ex_dt);
    public abstract void unstoringInsert(SqlSessionTemplate session, UnstoringDTO insertData);
    public abstract void unstoringDelete(SqlSessionTemplate session, UnstoringDTO deleteData);
    public abstract String ex_no_Cal(SqlSessionTemplate session, String corp_cd);
}
