package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.UnstoringReturnDTO;
import cse.knu.cdp1.dto.UnstoringReturnListDTO;
import cse.knu.cdp1.dto.UnstoringReturnDTO;
import cse.knu.cdp1.dto.UnstoringReturnListDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class UnstoringReturnDAOImpl implements UnstoringReturnDAO{
    @Override
    public List<UnstoringReturnDTO> unstoringReturnTotalList(SqlSessionTemplate session) {
        return session.selectList("unstoringReturnMapper.unstoringReturnTotalList");
    }

    @Override
    public List<UnstoringReturnListDTO> unstoringReturnSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo) {
        return session.selectList("unstoringReturnMapper.unstoringReturnSpecList", searchInfo);
    }

    @Override
    public UnstoringReturnDTO unstoringReturnOne(SqlSessionTemplate session, String ex_retu_no) {
        return session.selectOne("unstoringReturnMapper.unstoringReturnOne", ex_retu_no);
    }

    @Override
    public List<UnstoringReturnDTO> checkFormerUnstoringReturnList(SqlSessionTemplate session, String ex_retu_dt) {
        return session.selectList("unstoringReturnMapper.checkFormerUnstoringReturnList", ex_retu_dt);
    }

    @Override
    public void unstoringReturnInsert(SqlSessionTemplate session, UnstoringReturnDTO insertData) {
        session.insert("unstoringReturnMapper.unstoringReturnInsert", insertData);
    }

    @Override
    public void unstoringReturnDelete(SqlSessionTemplate session, UnstoringReturnDTO deleteData) {
        session.delete("unstoringReturnMapper.unstoringReturnDelete", deleteData);
    }

    @Override
    public String ex_retu_no_Cal(SqlSessionTemplate session, String corp_cd) {
        return session.selectOne("unstoringReturnMapper.unstoringReturnNo", corp_cd);
    }
}
