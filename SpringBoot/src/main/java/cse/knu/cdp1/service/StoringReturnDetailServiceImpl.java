package cse.knu.cdp1.service;

import cse.knu.cdp1.dao.StoringReturnDetailDAO;
import cse.knu.cdp1.dto.StoringReturnDetailDTO;
import cse.knu.cdp1.dto.StoringReturnListDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class StoringReturnDetailServiceImpl implements StoringReturnDetailService {
    @Autowired
    StoringReturnDetailDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public List<StoringReturnDetailDTO> storingReturnDetailList() {
        return dao.storingReturnDetailList(session);
    }

    @Override
    public List<StoringReturnListDTO> storingReturnDetailSpecList(HashMap<String, String> searchInfo) {
        return dao.storingReturnDetailSpecList(session, searchInfo);
    }

    @Override
    public void storingReturnDetailInsert(StoringReturnDetailDTO insertData) {
        dao.storingReturnDetailInsert(session, insertData);
    }

    @Override
    public void storingReturnDetailDelete(StoringReturnDetailDTO deleteData) {
        dao.storingReturnDetailDelete(session, deleteData);
    }

    @Override
    public List<StoringReturnDetailDTO> checkStoringReturnDetailList(String purc_retu_no) {
        return dao.checkStoringReturnDetailList(session, purc_retu_no);
    }

    @Override
    public void storingReturnDetailUpdate(StoringReturnDetailDTO updateData) {
        dao.storingReturnDetailUpdate(session, updateData);
    }
}
