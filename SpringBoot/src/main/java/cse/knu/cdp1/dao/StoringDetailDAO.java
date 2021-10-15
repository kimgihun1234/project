package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.StoringDetailDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface StoringDetailDAO {
    public abstract List<StoringDetailDTO> storingDetailList(SqlSessionTemplate session);
    public abstract void storingDetailInsert(SqlSessionTemplate session, StoringDetailDTO insertData);
    public abstract void storingDetailDelete(SqlSessionTemplate session, StoringDetailDTO deleteData);
}
