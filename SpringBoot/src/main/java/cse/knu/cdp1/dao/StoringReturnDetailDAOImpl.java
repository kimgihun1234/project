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
        return session.selectList("mapper.storingReturnDetailList");
    }

    @Override
    public List<StoringReturnListDTO> storingReturnDetailSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo) {
        return session.selectList("mapper.storingReturnDetailSpecList", searchInfo);
    }

    @Override
    public void storingReturnDetailInsert(SqlSessionTemplate session, StoringReturnDetailDTO insertData) {
        session.insert("mapper.storingReturnDetailInsert", insertData);
    }

    @Override
    public void storingReturnDetailDelete(SqlSessionTemplate session, StoringReturnDetailDTO deleteData) {
        session.delete("mapper.storingReturnDetailDelete", deleteData);
    }

    @Override
    public List<StoringReturnDetailDTO> checkFormerStoringReturnDetailList(SqlSessionTemplate session, String plord_no) {
        return session.selectList("mapper.checkFormerStoringReturnDetailList", plord_no);
    }

    @Override
    public List<StoringReturnDetailDTO> checkStoringReturnDetailList(SqlSessionTemplate session, String purc_retu_no) {
        return session.selectList("mapper.checkStoringReturnDetailList", purc_retu_no);
    }
}
