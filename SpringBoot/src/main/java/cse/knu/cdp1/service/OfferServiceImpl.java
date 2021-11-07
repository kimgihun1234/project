package cse.knu.cdp1.service;

import cse.knu.cdp1.dao.OfferDAO;
import cse.knu.cdp1.dto.OfferDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService{
    @Autowired
    OfferDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public List<OfferDTO> offerList() {
        return dao.offerList(session);
    }

    @Override
    public OfferDTO getOfferInfo(String ex_requ_no) {
        return dao.getOfferInfo(session, ex_requ_no);
    }
}
