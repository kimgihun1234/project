package cse.knu.cdp1.service;


import cse.knu.cdp1.dao.UnstoringDAO;
import cse.knu.cdp1.dto.UnstoringDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnstoringServiceImpl implements UnstoringService {
    @Autowired
    UnstoringDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public List<UnstoringDTO> unstoringList() {
        return dao.unstoringList(session);
    }

    @Override
    public void unstoringInsert(UnstoringDTO insertData) {
        dao.unstoringInsert(session, insertData);
    }

    @Override
    public void unstoringDelete(UnstoringDTO deleteData) {
        dao.unstoringDelete(session, deleteData);
    }
}
