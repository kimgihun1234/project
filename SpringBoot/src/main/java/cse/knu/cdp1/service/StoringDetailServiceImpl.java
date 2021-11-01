package cse.knu.cdp1.service;

import cse.knu.cdp1.dao.StoringDetailDAO;
import cse.knu.cdp1.dto.StoringDetailDTO;
import cse.knu.cdp1.dto.StoringListDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class StoringDetailServiceImpl implements StoringDetailService {
    @Autowired
    StoringDetailDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public List<StoringDetailDTO> storingDetailList() {
        return dao.storingDetailList(session);
    }

    @Override
    public List<StoringListDTO> storingDetailSpecList(HashMap<String, String> searchInfo) {
        return dao.storingDetailSpecList(session, searchInfo);
    }

    @Override
    public void storingDetailInsert(StoringDetailDTO insertData) {
        dao.storingDetailInsert(session, insertData);
    }

    @Override
    public void storingDetailDelete(StoringDetailDTO deleteData) {
        dao.storingDetailDelete(session, deleteData);
    }

    @Override
    public List<StoringDetailDTO> checkFormerDetailList(String plord_no) {
        return dao.checkFormerDetailList(session, plord_no);
    }

    @Override
    public List<StoringDetailDTO> checkDetailList(String purc_in_no){
        return dao.checkDetailList(session, purc_in_no);
    }
}
