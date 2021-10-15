package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.UnstoringDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UnstoringDAOImpl implements UnstoringDAO{
    @Override
    public List<UnstoringDTO> unstoringList(SqlSessionTemplate session) {
        return session.selectList("mapper.unstoringList");
    }

    public void unstoringInsert(SqlSessionTemplate session, UnstoringDTO insertData) {
        session.insert("mapper.unstoringInsert", insertData);
    }

    public void unstoringDelete(SqlSessionTemplate session, UnstoringDTO deleteData) {
        session.delete("mapper.unstoringDelete", deleteData);
    }
}
