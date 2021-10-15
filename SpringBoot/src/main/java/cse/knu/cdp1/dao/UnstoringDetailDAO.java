package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.UnstoringDetailDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface UnstoringDetailDAO {
    public abstract List<UnstoringDetailDTO> unstoringDetailList(SqlSessionTemplate session);
    public abstract void unstoringDetailInsert(SqlSessionTemplate session, UnstoringDetailDTO insertData);
    public abstract void unstoringDetailDelete(SqlSessionTemplate session, UnstoringDetailDTO deleteData);
}
