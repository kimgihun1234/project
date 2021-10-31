package cse.knu.cdp1.service;

import cse.knu.cdp1.dao.BarcodeDAO;
import cse.knu.cdp1.dao.CustomerDAO;
import cse.knu.cdp1.dto.BarcodeDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarcodeServiceImpl implements BarcodeService {
    @Autowired
    BarcodeDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public BarcodeDTO getBarcodeInfo(String barcode) {
        return dao.getBarcodeInfo(session, barcode);
    }
}
