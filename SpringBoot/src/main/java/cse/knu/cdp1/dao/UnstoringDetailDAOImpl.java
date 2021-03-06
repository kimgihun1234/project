package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.UnstoringDetailDTO;
import cse.knu.cdp1.dto.UnstoringListDTO;
import cse.knu.cdp1.dto.UnstoringDetailDTO;
import cse.knu.cdp1.dto.UnstoringListDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class UnstoringDetailDAOImpl implements UnstoringDetailDAO {
    @Override
    public List<UnstoringDetailDTO> unstoringDetailList(SqlSessionTemplate session) {
        return session.selectList("unstoringMapper.unstoringDetailList");
    }

    @Override
    public List<UnstoringListDTO> unstoringDetailSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo) {
        return session.selectList("unstoringMapper.unstoringDetailSpecList", searchInfo);
    }

    @Override
    public void unstoringDetailInsert(SqlSessionTemplate session, UnstoringDetailDTO insertData) {
        session.insert("unstoringMapper.unstoringDetailInsert", insertData);
    }

    @Override
    public void unstoringDetailDelete(SqlSessionTemplate session, UnstoringDetailDTO deleteData) {
        session.delete("unstoringMapper.unstoringDetailDelete", deleteData);
    }

    @Override
    public List<UnstoringDetailDTO> checkUnstoringDetailList(SqlSessionTemplate session, String ex_no) {
        return session.selectList("unstoringMapper.checkUnstoringDetailList", ex_no);
    }

    @Override
    public void unstoringDetailUpdate(SqlSessionTemplate session, UnstoringDetailDTO updateData) {
        session.update("unstoringMapper.unstoringDetailUpdate", updateData);
    }
}
