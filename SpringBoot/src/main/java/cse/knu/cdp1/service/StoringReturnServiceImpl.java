package cse.knu.cdp1.service;

import cse.knu.cdp1.dao.StoringReturnDAO;
import cse.knu.cdp1.dto.StoringDTO;
import cse.knu.cdp1.dto.StoringReturnDTO;
import cse.knu.cdp1.dto.StoringReturnListDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class StoringReturnServiceImpl implements StoringReturnService{
    @Autowired
    StoringReturnDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public List<StoringReturnDTO> storingReturnTotalList() {
        return dao.storingReturnTotalList(session);
    }

    @Override
    public List<StoringReturnListDTO> storingReturnSpecList(HashMap<String, String> searchInfo) {
        return dao.storingReturnSpecList(session, searchInfo);
    }

    @Override
    public StoringReturnDTO storingReturnOne(String purc_retu_no) {
        return dao.storingReturnOne(session, purc_retu_no);
    }

    @Override
    public String calStoringReturnDate(HashMap<String, String> searchInfo) {
        return dao.calStoringReturnDate(session, searchInfo);
    }

    @Override
    public double calStoringReturnTotalSum(HashMap<String, String> searchInfo) {
        return dao.calStoringReturnTotalSum(session, searchInfo);
    }

    @Override
    public void storingReturnInsert(StoringReturnDTO insertData) {
        dao.storingReturnInsert(session, insertData);
    }

    @Override
    public void storingReturnDelete(StoringReturnDTO deleteData) {
        dao.storingReturnDelete(session, deleteData);
    }

    @Override
    public String purc_retu_no_Cal(String corp_cd) {
        return dao.purc_retu_no_Cal(session, corp_cd);
    }
}
