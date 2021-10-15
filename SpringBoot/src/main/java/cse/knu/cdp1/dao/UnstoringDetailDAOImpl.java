package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.UnstoringDetailDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UnstoringDetailDAOImpl implements UnstoringDetailDAO {
    @Override
    public List<UnstoringDetailDTO> unstoringDetailList(SqlSessionTemplate session) {
        return session.selectList("mapper.unstoringDetailList");
    }

    public void unstoringDetailInsert(SqlSessionTemplate session, UnstoringDetailDTO insertData) {
        session.insert("mapper.unstoringDetailInsert", insertData);
    }

    public void unstoringDetailDelete(SqlSessionTemplate session, UnstoringDetailDTO deleteData) {
        session.delete("mapper.unstoringDetailDelete", deleteData);
    }
}
