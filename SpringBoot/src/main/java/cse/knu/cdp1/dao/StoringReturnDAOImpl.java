package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.StoringDTO;
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
        return session.selectList("mapper.storingReturnTotalList");
    }

    @Override
    public List<StoringReturnListDTO> storingReturnSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo) {
        return session.selectList("mapper.storingReturnSpecList", searchInfo);
    }

    @Override
    public StoringReturnDTO storingReturnOne(SqlSessionTemplate session, String purc_retu_no) {
        return session.selectOne("mapper.storingReturnOne", purc_retu_no);
    }

    @Override
    public String calStoringReturnDate(SqlSessionTemplate session, HashMap<String, String> searchInfo) {
        return session.selectOne("mapper.calStoringReturnDate", searchInfo);
    }

    @Override
    public double calStoringReturnTotalSum(SqlSessionTemplate session, HashMap<String, String> searchInfo) {
        return session.selectOne("mapper.calStoringReturnTotalSum", searchInfo);
    }

    @Override
    public void storingReturnInsert(SqlSessionTemplate session, StoringReturnDTO insertData) {
        session.insert("mapper.storingReturnInsert", insertData);
    }

    @Override
    public void storingReturnDelete(SqlSessionTemplate session, StoringReturnDTO deleteData) {
        session.delete("mapper.storingReturnDelete", deleteData);
    }

    @Override
    public String purc_retu_no_Cal(SqlSessionTemplate session, String corp_cd) {
        return session.selectOne("mapper.storingReturnNo", corp_cd);
    }
}
