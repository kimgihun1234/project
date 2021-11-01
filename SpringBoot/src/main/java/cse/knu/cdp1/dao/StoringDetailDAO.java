package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.StoringDetailDTO;
import cse.knu.cdp1.dto.StoringListDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.HashMap;
import java.util.List;

public interface StoringDetailDAO {
    public abstract List<StoringDetailDTO> storingDetailList(SqlSessionTemplate session);
    public abstract List<StoringListDTO> storingDetailSpecList(SqlSessionTemplate session, HashMap<String, String> searchInfo);
    public abstract void storingDetailInsert(SqlSessionTemplate session, StoringDetailDTO insertData);
    public abstract void storingDetailDelete(SqlSessionTemplate session, StoringDetailDTO deleteData);
    public abstract List<StoringDetailDTO> checkFormerDetailList(SqlSessionTemplate session, String plord_no);
    public abstract List<StoringDetailDTO> checkDetailList(SqlSessionTemplate session, String purc_in_no);
}
