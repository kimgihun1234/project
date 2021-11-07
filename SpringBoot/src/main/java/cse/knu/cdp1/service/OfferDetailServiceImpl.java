package cse.knu.cdp1.service;

import cse.knu.cdp1.dao.OfferDetailDAO;
import cse.knu.cdp1.dto.OfferDetailDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferDetailServiceImpl implements OfferDetailService{
    @Autowired
    OfferDetailDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public List<OfferDetailDTO> offerDetailList() {
        return dao.offerDetailList(session);
    }
}
