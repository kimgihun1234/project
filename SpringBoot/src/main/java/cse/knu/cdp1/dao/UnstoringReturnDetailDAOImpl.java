package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.UnstoringReturnDetailDTO;
import cse.knu.cdp1.dto.UnstoringReturnListDTO;
import cse.knu.cdp1.dto.UnstoringReturnDetailDTO;
import cse.knu.cdp1.dto.UnstoringReturnListDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class UnstoringReturnDetailDAOImpl implements UnstoringReturnDetailDAO{
    @Override
    public List<UnstoringReturnDetailDTO> unstoringReturnDetailList(SqlSessionTemplate session) {
        return session.selectList("unstoringReturnMapper.unstoringReturnDetailList");
    }

    @Override
    public List<UnstoringReturnListDTO> unstoringReturnDetailSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo) {
        return session.selectList("unstoringReturnMapper.unstoringReturnDetailSpecList", searchInfo);
    }

    @Override
    public void unstoringReturnDetailInsert(SqlSessionTemplate session, UnstoringReturnDetailDTO insertData) {
        session.insert("unstoringReturnMapper.unstoringReturnDetailInsert", insertData);
    }

    @Override
    public void unstoringReturnDetailDelete(SqlSessionTemplate session, UnstoringReturnDetailDTO deleteData) {
        session.delete("unstoringReturnMapper.unstoringReturnDetailDelete", deleteData);
    }

    @Override
    public List<UnstoringReturnDetailDTO> checkUnstoringReturnDetailList(SqlSessionTemplate session, String ex_retu_no) {
        return session.selectList("unstoringReturnMapper.checkUnstoringReturnDetailList", ex_retu_no);
    }

    @Override
    public void unstoringReturnDetailUpdate(SqlSessionTemplate session, UnstoringReturnDetailDTO updateData) {
        session.update("unstoringReturnMapper.unstoringReturnDetailUpdate", updateData);
    }
}
