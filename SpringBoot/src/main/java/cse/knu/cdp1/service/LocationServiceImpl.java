package cse.knu.cdp1.service;

import cse.knu.cdp1.dao.LocationDAO;
import cse.knu.cdp1.dto.LocationDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{
    @Autowired
    LocationDAO dao;

    @Autowired
    SqlSessionTemplate session;

    @Override
    public List<LocationDTO> locationList() {
        return dao.locationList(session);
    }

    @Override
    public LocationDTO getLocationInfo(HashMap<String, String> searchInfo) {
        return dao.getLocationInfo(session, searchInfo);
    }
}
