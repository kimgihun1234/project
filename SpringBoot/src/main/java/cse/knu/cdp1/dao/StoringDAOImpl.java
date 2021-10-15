package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.StoringDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StoringDAOImpl implements StoringDAO{
    @Override
    public List<StoringDTO> storingList(SqlSessionTemplate session) {
        return session.selectList("mapper.storingList");
    }

    public void storingInsert(SqlSessionTemplate session, StoringDTO insertData) {
        session.insert("mapper.storingInsert", insertData);
    }

    public void storingDelete(SqlSessionTemplate session, StoringDTO deleteData) {
        session.delete("mapper.storingDelete", deleteData);
    }
}
