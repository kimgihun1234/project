package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.StoringDetailDTO;
import cse.knu.cdp1.dto.StoringReturnDetailDTO;
import cse.knu.cdp1.dto.StoringReturnListDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.HashMap;
import java.util.List;

public interface StoringReturnDetailDAO {
    public abstract List<StoringReturnDetailDTO> storingReturnDetailList(SqlSessionTemplate session);
    public abstract List<StoringReturnListDTO> storingReturnDetailSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo);
    public abstract void storingReturnDetailInsert(SqlSessionTemplate session, StoringReturnDetailDTO insertData);
    public abstract void storingReturnDetailDelete(SqlSessionTemplate session, StoringReturnDetailDTO deleteData);
    public abstract List<StoringReturnDetailDTO> checkStoringReturnDetailList(SqlSessionTemplate session, String purc_retu_no);
    public abstract void storingReturnDetailUpdate(SqlSessionTemplate session, StoringReturnDetailDTO updateData);
}
