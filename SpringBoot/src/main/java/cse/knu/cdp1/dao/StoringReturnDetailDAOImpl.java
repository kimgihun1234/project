package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.StoringReturnDetailDTO;
import cse.knu.cdp1.dto.StoringReturnListDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class StoringReturnDetailDAOImpl implements StoringReturnDetailDAO{
    @Override
    public List<StoringReturnDetailDTO> storingReturnDetailList(SqlSessionTemplate session) {
        return session.selectList("storingReturnMapper.storingReturnDetailList");
    }

    @Override
    public List<StoringReturnListDTO> storingReturnDetailSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo) {
        return session.selectList("storingReturnMapper.storingReturnDetailSpecList", searchInfo);
    }

    @Override
    public void storingReturnDetailInsert(SqlSessionTemplate session, StoringReturnDetailDTO insertData) {
        session.insert("storingReturnMapper.storingReturnDetailInsert", insertData);
    }

    @Override
    public void storingReturnDetailDelete(SqlSessionTemplate session, StoringReturnDetailDTO deleteData) {
        session.delete("storingReturnMapper.storingReturnDetailDelete", deleteData);
    }

    @Override
    public List<StoringReturnDetailDTO> checkStoringReturnDetailList(SqlSessionTemplate session, String purc_retu_no) {
        return session.selectList("storingReturnMapper.checkStoringReturnDetailList", purc_retu_no);
    }

    @Override
    public void storingReturnDetailUpdate(SqlSessionTemplate session, StoringReturnDetailDTO updateData) {
        session.update("storingReturnMapper.storingReturnDetailUpdate", updateData);
    }
}
