package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.UnstoringDetailDTO;
import cse.knu.cdp1.dto.UnstoringListDTO;
import cse.knu.cdp1.dto.UnstoringDetailDTO;
import cse.knu.cdp1.dto.UnstoringListDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.HashMap;
import java.util.List;

public interface UnstoringDetailDAO {
    public abstract List<UnstoringDetailDTO> unstoringDetailList(SqlSessionTemplate session);
    public abstract List<UnstoringListDTO> unstoringDetailSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo);
    public abstract void unstoringDetailInsert(SqlSessionTemplate session, UnstoringDetailDTO insertData);
    public abstract void unstoringDetailDelete(SqlSessionTemplate session, UnstoringDetailDTO deleteData);
    public abstract List<UnstoringDetailDTO> checkUnstoringDetailList(SqlSessionTemplate session, String ex_no);
    public abstract void unstoringDetailUpdate(SqlSessionTemplate session, UnstoringDetailDTO updateData);
}
