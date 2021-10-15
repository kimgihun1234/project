package cse.knu.cdp1.service;

import cse.knu.cdp1.dao.UnstoringDetailDAO;
import cse.knu.cdp1.dto.UnstoringDetailDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnstoringDetailServiceImpl implements UnstoringDetailService {
    @Autowired
    UnstoringDetailDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public List<UnstoringDetailDTO> unstoringDetailList() {
        return dao.unstoringDetailList(session);
    }

    @Override
    public void unstoringDetailInsert(UnstoringDetailDTO insertData) {
        dao.unstoringDetailInsert(session, insertData);
    }

    @Override
    public void unstoringDetailDelete(UnstoringDetailDTO deleteData) {
        dao.unstoringDetailDelete(session, deleteData);
    }
}
