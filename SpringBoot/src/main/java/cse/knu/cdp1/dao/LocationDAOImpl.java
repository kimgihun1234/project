package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.LocationDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class LocationDAOImpl implements LocationDAO{
    @Override
    public List<LocationDTO> locationList(SqlSessionTemplate session) {
        return session.selectList("mapper.locationList");
    }

    @Override
    public LocationDTO getLocationInfo(SqlSessionTemplate session, HashMap<String, String> searchInfo) {
        return session.selectOne("mapper.getLocationInfo", searchInfo);
    }
}
