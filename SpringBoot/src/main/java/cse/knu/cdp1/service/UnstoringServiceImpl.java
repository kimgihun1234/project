package cse.knu.cdp1.service;


import cse.knu.cdp1.dao.UnstoringDAO;
import cse.knu.cdp1.dto.UnstoringDTO;
import cse.knu.cdp1.dto.UnstoringListDTO;
import cse.knu.cdp1.dto.UnstoringDTO;
import cse.knu.cdp1.dto.UnstoringListDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UnstoringServiceImpl implements UnstoringService {
    @Autowired
    UnstoringDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public List<UnstoringDTO> unstoringTotalList() {
        return dao.unstoringTotalList(session);
    }

    @Override
    public UnstoringDTO unstoringOne(String purc_in_no) {
        return dao.unstoringOne(session, purc_in_no);
    }

    @Override
    public List<UnstoringListDTO> unstoringSpecList(HashMap<String, String> searchInfo) {
        return dao.unstoringSpecList(session, searchInfo);
    }

    @Override
    public List<UnstoringDTO> checkFormerUnstoringList() {
        SimpleDateFormat format1 = new SimpleDateFormat( "yyyyMMdd");
        Date time = new Date();
        return dao.checkFormerUnstoringList(session, format1.format(time));
    }

    @Override
    public void unstoringInsert(UnstoringDTO insertData) {
        dao.unstoringInsert(session, insertData);
    }

    @Override
    public void unstoringDelete(UnstoringDTO deleteData) {
        dao.unstoringDelete(session, deleteData);
    }

    @Override
    public String ex_no_Cal(String corp_cd) {
        return dao.ex_no_Cal(session, corp_cd);
    }
}