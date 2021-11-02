package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.StoringDetailDTO;
import cse.knu.cdp1.dto.StoringListDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class StoringDetailDAOImpl implements StoringDetailDAO {
    @Override
    public List<StoringDetailDTO> storingDetailList(SqlSessionTemplate session) {
        return session.selectList("mapper.storingDetailList");
    }

    @Override
    public List<StoringDetailDTO> storingDetailOne(SqlSessionTemplate session, HashMap<String, String> searchInfo) {
        return session.selectList("mapper.storingDetailOne", searchInfo);
    }

    @Override
    public List<StoringListDTO> storingDetailSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo) {
        return session.selectList("mapper.storingDetailSpecList", searchInfo);
    }

    @Override
    public void storingDetailInsert(SqlSessionTemplate session, StoringDetailDTO insertData) {
        session.insert("mapper.storingDetailInsert", insertData);
    }

    @Override
    public void storingDetailDelete(SqlSessionTemplate session, StoringDetailDTO deleteData) {
        session.delete("mapper.storingDetailDelete", deleteData);
    }

    @Override
    public List<StoringDetailDTO> checkFormerStoringDetailList(SqlSessionTemplate session, String plord_no) {
        return session.selectList("mapper.checkFormerStoringDetailList", plord_no);
    }

    @Override
    public List<StoringDetailDTO> checkStoringDetailList(SqlSessionTemplate session, String purc_in_no) {
        return session.selectList("mapper.checkStoringDetailList", purc_in_no);
    }
}
