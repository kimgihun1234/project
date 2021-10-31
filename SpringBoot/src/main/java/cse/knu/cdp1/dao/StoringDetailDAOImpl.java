package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.StoringDetailDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StoringDetailDAOImpl implements StoringDetailDAO {
    @Override
    public List<StoringDetailDTO> storingDetailList(SqlSessionTemplate session) {
        return session.selectList("mapper.storingDetailList");
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
    public List<StoringDetailDTO> checkFormerDetailList(SqlSessionTemplate session, String plord_no) {
        return session.selectList("mapper.checkFormerDetailList", plord_no);
    }
}
