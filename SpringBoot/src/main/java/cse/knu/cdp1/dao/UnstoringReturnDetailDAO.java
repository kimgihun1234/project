package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.UnstoringReturnDetailDTO;
import cse.knu.cdp1.dto.UnstoringReturnListDTO;
import cse.knu.cdp1.dto.UnstoringReturnDetailDTO;
import cse.knu.cdp1.dto.UnstoringReturnListDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.HashMap;
import java.util.List;

public interface UnstoringReturnDetailDAO {
    public abstract List<UnstoringReturnDetailDTO> unstoringReturnDetailList(SqlSessionTemplate session);
    public abstract List<UnstoringReturnListDTO> unstoringReturnDetailSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo);
    public abstract void unstoringReturnDetailInsert(SqlSessionTemplate session, UnstoringReturnDetailDTO insertData);
    public abstract void unstoringReturnDetailDelete(SqlSessionTemplate session, UnstoringReturnDetailDTO deleteData);
    public abstract List<UnstoringReturnDetailDTO> checkUnstoringReturnDetailList(SqlSessionTemplate session, String ex_retu_no);
    public abstract void unstoringReturnDetailUpdate(SqlSessionTemplate session, UnstoringReturnDetailDTO updateData);
}