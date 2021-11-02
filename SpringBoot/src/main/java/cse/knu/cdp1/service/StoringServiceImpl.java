package cse.knu.cdp1.service;


import cse.knu.cdp1.dto.StoringListDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cse.knu.cdp1.dao.StoringDAO;
import cse.knu.cdp1.dto.StoringDTO;

import java.util.HashMap;
import java.util.List;

@Service
public class StoringServiceImpl implements StoringService {
    @Autowired
    StoringDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public List<StoringDTO> storingTotalList() {
        return dao.storingTotalList(session);
    }

    @Override
    public StoringDTO storingOne(String purc_in_no) {
        return dao.storingOne(session, purc_in_no);
    }

    @Override
    public List<StoringListDTO> storingSpecList(HashMap<String, String> searchInfo) {
        return dao.storingSpecList(session, searchInfo);
    }

    @Override
    public void storingInsert(StoringDTO insertData) {
        dao.storingInsert(session, insertData);
    }

    @Override
    public void storingDelete(StoringDTO deleteData) {
        dao.storingDelete(session, deleteData);
    }

    @Override
    public String purc_in_no_Cal(String corp_cd) {
        return dao.purc_in_no_Cal(session, corp_cd);
    }
}
