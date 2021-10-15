package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.UnstoringDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface UnstoringDAO {
    public abstract List<UnstoringDTO> unstoringList(SqlSessionTemplate session);
    public abstract void unstoringInsert(SqlSessionTemplate session, UnstoringDTO insertData);
    public abstract void unstoringDelete(SqlSessionTemplate session, UnstoringDTO deleteData);
}
