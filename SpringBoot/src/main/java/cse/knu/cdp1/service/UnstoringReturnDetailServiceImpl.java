package cse.knu.cdp1.service;

import cse.knu.cdp1.dao.UnstoringReturnDetailDAO;
import cse.knu.cdp1.dto.UnstoringReturnDetailDTO;
import cse.knu.cdp1.dto.UnstoringReturnListDTO;
import cse.knu.cdp1.dto.UnstoringReturnDetailDTO;
import cse.knu.cdp1.dto.UnstoringReturnListDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UnstoringReturnDetailServiceImpl implements UnstoringReturnDetailService{
    @Autowired
    UnstoringReturnDetailDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public List<UnstoringReturnDetailDTO> unstoringReturnDetailList() {
        return dao.unstoringReturnDetailList(session);
    }

    @Override
    public List<UnstoringReturnListDTO> unstoringReturnDetailSpecList(HashMap<String, String> searchInfo) {
        return dao.unstoringReturnDetailSpecList(session, searchInfo);
    }

    @Override
    public void unstoringReturnDetailInsert(UnstoringReturnDetailDTO insertData) {
        dao.unstoringReturnDetailInsert(session, insertData);
    }

    @Override
    public void unstoringReturnDetailDelete(UnstoringReturnDetailDTO deleteData) {
        dao.unstoringReturnDetailDelete(session, deleteData);
    }

    @Override
    public List<UnstoringReturnDetailDTO> checkUnstoringReturnDetailList(String ex_retu_no) {
        return dao.checkUnstoringReturnDetailList(session, ex_retu_no);
    }

    @Override
    public void unstoringReturnDetailUpdate(UnstoringReturnDetailDTO updateData) {
        dao.unstoringReturnDetailUpdate(session, updateData);
    }
}
