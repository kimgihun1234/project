package cse.knu.cdp1.service;

import cse.knu.cdp1.dto.LocationDTO;

import java.util.HashMap;
import java.util.List;

public interface LocationService {
    public abstract List<LocationDTO> locationList();
    public abstract LocationDTO getLocationInfo(HashMap<String, String> searchInfo);
}
