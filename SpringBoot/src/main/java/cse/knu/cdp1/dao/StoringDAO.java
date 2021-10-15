package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.StoringDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface StoringDAO {
    public abstract List<StoringDTO> storingList(SqlSessionTemplate session);
    public abstract void storingInsert(SqlSessionTemplate session, StoringDTO insertData);
    public abstract void storingDelete(SqlSessionTemplate session, StoringDTO deleteData);
}
