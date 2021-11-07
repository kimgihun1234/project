package cse.knu.cdp1.service;

import cse.knu.cdp1.dao.UnstoringReturnDAO;
import cse.knu.cdp1.dto.UnstoringReturnDTO;
import cse.knu.cdp1.dto.UnstoringReturnListDTO;
import cse.knu.cdp1.dto.UnstoringReturnDTO;
import cse.knu.cdp1.dto.UnstoringReturnListDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UnstoringReturnServiceImpl implements UnstoringReturnService{
    @Autowired
    UnstoringReturnDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public List<UnstoringReturnDTO> unstoringReturnTotalList() {
        return dao.unstoringReturnTotalList(session);
    }

    @Override
    public List<UnstoringReturnListDTO> unstoringReturnSpecList(HashMap<String, String> searchInfo) {
        return dao.unstoringReturnSpecList(session, searchInfo);
    }

    @Override
    public UnstoringReturnDTO unstoringReturnOne(String ex_retu_no) {
        return dao.unstoringReturnOne(session, ex_retu_no);
    }

    @Override
    public List<UnstoringReturnDTO> checkFormerUnstoringReturnList() {
        SimpleDateFormat format1 = new SimpleDateFormat( "yyyyMMdd");
        Date time = new Date();
        return dao.checkFormerUnstoringReturnList(session, format1.format(time));
    }

    @Override
    public void unstoringReturnInsert(UnstoringReturnDTO insertData) {
        dao.unstoringReturnInsert(session, insertData);
    }

    @Override
    public void unstoringReturnDelete(UnstoringReturnDTO deleteData) {
        dao.unstoringReturnDelete(session, deleteData);
    }

    @Override
    public String ex_retu_no_Cal(String corp_cd) {
        return dao.ex_retu_no_Cal(session, corp_cd);
    }
}
