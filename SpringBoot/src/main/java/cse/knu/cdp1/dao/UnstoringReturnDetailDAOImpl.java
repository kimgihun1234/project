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
        return session.selectList("mapper.unstoringReturnDetailList");
    }

    @Override
    public List<UnstoringReturnListDTO> unstoringReturnDetailSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo) {
        return session.selectList("mapper.unstoringReturnDetailSpecList", searchInfo);
    }

    @Override
    public void unstoringReturnDetailInsert(SqlSessionTemplate session, UnstoringReturnDetailDTO insertData) {
        session.insert("mapper.unstoringReturnDetailInsert", insertData);
    }

    @Override
    public void unstoringReturnDetailDelete(SqlSessionTemplate session, UnstoringReturnDetailDTO deleteData) {
        session.delete("mapper.unstoringReturnDetailDelete", deleteData);
    }

    @Override
    public List<UnstoringReturnDetailDTO> checkUnstoringReturnDetailList(SqlSessionTemplate session, String ex_retu_no) {
        return session.selectList("mapper.checkUnstoringReturnDetailList", ex_retu_no);
    }

    @Override
    public void unstoringReturnDetailUpdate(SqlSessionTemplate session, UnstoringReturnDetailDTO updateData) {
        session.update("mapper.unstoringReturnDetailUpdate", updateData);
    }
}
