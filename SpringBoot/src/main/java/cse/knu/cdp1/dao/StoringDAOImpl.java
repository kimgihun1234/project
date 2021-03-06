package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.StoringDTO;
import cse.knu.cdp1.dto.StoringListDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class StoringDAOImpl implements StoringDAO{
    @Override
    public List<StoringDTO> storingTotalList(SqlSessionTemplate session) {
        return session.selectList("storingMapper.storingTotalList");
    }

    @Override
    public StoringDTO storingOne(SqlSessionTemplate session, String purc_in_no) {
        return session.selectOne("storingMapper.storingOne", purc_in_no);
    }

    @Override
    public List<StoringListDTO> storingSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo) {
        return session.selectList("storingMapper.storingSpecList", searchInfo);
    }

    @Override
    public List<StoringDTO> checkFormerStoringList(SqlSessionTemplate session, String purc_in_dt) {
        return session.selectList("storingMapper.checkFormerStoringList", purc_in_dt);
    }

    @Override
    public void storingInsert(SqlSessionTemplate session, StoringDTO insertData) {
        session.insert("storingMapper.storingInsert", insertData);
    }

    @Override
    public void storingDelete(SqlSessionTemplate session, StoringDTO deleteData) {
        session.delete("storingMapper.storingDelete", deleteData);
    }

    @Override
    public String purc_in_no_Cal(SqlSessionTemplate session, String corp_cd) {
        return session.selectOne("storingMapper.storingNo", corp_cd);
    }
}
