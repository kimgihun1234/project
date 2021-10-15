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

    public void storingDetailInsert(SqlSessionTemplate session, StoringDetailDTO insertData) {
        session.insert("mapper.storingDetailInsert", insertData);
    }

    public void storingDetailDelete(SqlSessionTemplate session, StoringDetailDTO deleteData) {
        session.delete("mapper.storingDetailDelete", deleteData);
    }
}
