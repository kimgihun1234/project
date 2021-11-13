package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.UnstoringDTO;
import cse.knu.cdp1.dto.UnstoringListDTO;
import cse.knu.cdp1.dto.UnstoringDTO;
import cse.knu.cdp1.dto.UnstoringListDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class UnstoringDAOImpl implements UnstoringDAO{
    @Override
    public List<UnstoringDTO> unstoringTotalList(SqlSessionTemplate session) {
        return session.selectList("unstoringMapper.unstoringTotalList");
    }

    @Override
    public UnstoringDTO unstoringOne(SqlSessionTemplate session, String ex_no) {
        return session.selectOne("unstoringMapper.unstoringOne", ex_no);
    }

    @Override
    public List<UnstoringListDTO> unstoringSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo) {
        return session.selectList("unstoringMapper.unstoringSpecList", searchInfo);
    }

    @Override
    public List<UnstoringDTO> checkFormerUnstoringList(SqlSessionTemplate session, String ex_dt) {
        return session.selectList("unstoringMapper.checkFormerUnstoringList", ex_dt);
    }

    @Override
    public void unstoringInsert(SqlSessionTemplate session, UnstoringDTO insertData) {
        session.insert("unstoringMapper.unstoringInsert", insertData);
    }

    @Override
    public void unstoringDelete(SqlSessionTemplate session, UnstoringDTO deleteData) {
        session.delete("unstoringMapper.unstoringDelete", deleteData);
    }

    @Override
    public String ex_no_Cal(SqlSessionTemplate session, String corp_cd) {
        return session.selectOne("unstoringMapper.unstoringNo", corp_cd);
    }
}
