package cse.knu.cdp1.service;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cse.knu.cdp1.dao.StoringDAO;
import cse.knu.cdp1.dto.StoringDTO;

import java.util.List;

@Service
public class StoringServiceImpl implements StoringService {
    @Autowired
    StoringDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public List<StoringDTO> storingList() {
        return dao.storingList(session);
    }

    @Override
    public void storingInsert(StoringDTO insertData) {
        dao.storingInsert(session, insertData);
    }

    @Override
    public void storingDelete(StoringDTO deleteData) {
        dao.storingDelete(session, deleteData);
    }
}