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
        return session.selectList("storingMapper.storingDetailList");
    }

    @Override
    public List<StoringListDTO> storingDetailSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo) {
        return session.selectList("storingMapper.storingDetailSpecList", searchInfo);
    }

    @Override
    public void storingDetailInsert(SqlSessionTemplate session, StoringDetailDTO insertData) {
        session.insert("storingMapper.storingDetailInsert", insertData);
    }

    @Override
    public void storingDetailDelete(SqlSessionTemplate session, StoringDetailDTO deleteData) {
        session.delete("storingMapper.storingDetailDelete", deleteData);
    }

    @Override
    public List<StoringDetailDTO> checkStoringDetailList(SqlSessionTemplate session, String purc_in_no) {
        return session.selectList("storingMapper.checkStoringDetailList", purc_in_no);
    }

    @Override
    public void storingDetailUpdate(SqlSessionTemplate session, StoringDetailDTO updateData) {
        session.update("storingMapper.storingDetailUpdate", updateData);
    }
}
