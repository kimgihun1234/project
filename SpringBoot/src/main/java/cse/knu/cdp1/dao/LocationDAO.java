package cse.knu.cdp1.dao;

import cse.knu.cdp1.dto.LocationDTO;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface LocationDAO {
    public abstract List<LocationDTO> locationList(SqlSessionTemplate session);
}
