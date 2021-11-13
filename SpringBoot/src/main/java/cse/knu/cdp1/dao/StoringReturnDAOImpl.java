package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.StoringReturnDTO;
import cse.knu.cdp1.dto.StoringReturnListDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class StoringReturnDAOImpl implements StoringReturnDAO{
    @Override
    public List<StoringReturnDTO> storingReturnTotalList(SqlSessionTemplate session) {
        return session.selectList("storingReturnMapper.storingReturnTotalList");
    }

    @Override
    public List<StoringReturnListDTO> storingReturnSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo) {
        return session.selectList("storingReturnMapper.storingReturnSpecList", searchInfo);
    }

    @Override
    public StoringReturnDTO storingReturnOne(SqlSessionTemplate session, String purc_retu_no) {
        return session.selectOne("storingReturnMapper.storingReturnOne", purc_retu_no);
    }

    @Override
    public List<StoringReturnDTO> checkFormerStoringReturnList(SqlSessionTemplate session, String purc_retu_dt) {
        return session.selectList("storingReturnMapper.checkFormerStoringReturnList", purc_retu_dt);
    }

    @Override
    public void storingReturnInsert(SqlSessionTemplate session, StoringReturnDTO insertData) {
        session.insert("storingReturnMapper.storingReturnInsert", insertData);
    }

    @Override
    public void storingReturnDelete(SqlSessionTemplate session, StoringReturnDTO deleteData) {
        session.delete("storingReturnMapper.storingReturnDelete", deleteData);
    }

    @Override
    public String purc_retu_no_Cal(SqlSessionTemplate session, String corp_cd) {
        return session.selectOne("storingReturnMapper.storingReturnNo", corp_cd);
    }
}
